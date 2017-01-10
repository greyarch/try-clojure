(ns try-clojure.app
  (:require
    [rum.core   :as rum]
    [rum.mdl    :as mdl]
    [try-clojure.db :as db]
    [try-clojure.ui :as ui]))

(def current-path [:counter :current])
(def next-path [:counter :next])
(def root (db/connect "https://try-clojure.firebaseio.com"))
(def state (db/sync (atom {}) root []))
(def current-count (rum/cursor-in state current-path))
(def next-count (rum/cursor-in state next-path))

(rum/defc counter-card < rum/reactive
  [title cursor path]
  (ui/counter-card title
    (rum/react cursor)
    #(db/update! root path inc)
    #(db/update! root path dec)))

(rum/defc app < rum/static
  []
  [:div {:style {:padding 30}}
    (counter-card "A counter" current-count current-path)
    (counter-card "Another counter" next-count next-path)])


(defn init []
  (rum/mount (app) (. js/document (getElementById "container"))))
