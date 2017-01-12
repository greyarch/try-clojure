(ns try-clojure.app
  (:require
    [rum.core   :as rum]
    [try-clojure.db :as db]
    [try-clojure.ui :as ui]))

(def root (db/connect "https://try-clojure.firebaseio.com/counter"))
(def state (db/sync (atom {}) root []))

(rum/defc counter-card < rum/reactive
  [title path]
  (ui/counter-card title
    (rum/react (rum/cursor-in state path))
    #(db/update! root path inc)
    #(db/update! root path dec)))

(rum/defc app < rum/static
  []
  [:div {:style {:padding 30}}
    (counter-card "A counter" [:current])
    (counter-card "Another counter" [:next])])


(defn init []
  (rum/mount (app) (. js/document (getElementById "container"))))
