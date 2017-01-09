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

(defn inc! [path]
  (fn [] (db/inc! root path)))

(defn dec! [path]
  (fn [] (db/dec! root path)))

(rum/defc app < rum/reactive
  []
  [:div {:style {:padding 30}}
    (ui/counter-card "A counter"
      (rum/react current-count) (inc! current-path) (dec! current-path))
    (ui/counter-card "Another counter"
      (rum/react next-count) #(inc! next-path) #(dec! next-path))])


(defn init []
  (rum/mount (app) (. js/document (getElementById "container"))))
