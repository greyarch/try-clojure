(ns try-rum.db
    (:require
      [matchbox.core :as m]))

(def root (m/connect "https://examiner-598ae.firebaseio.com"))
(m/auth-anon root)
(def path [:counter :current])
(def curr-counter (m/get-in root path))

(defn sync [store]
  (m/listen-children
    root [:counter]
    (fn [[event-type data]]
      (swap! store #(second data))))
  store)

(defn increment []
  (m/swap! curr-counter inc))
