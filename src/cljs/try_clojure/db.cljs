(ns try-clojure.db
    (:require
      [matchbox.core :as m]))

(defn connect
  [url]
  (let [root (m/connect url)]
    (m/auth-anon root)
    root))

(defn sync
  [store root path]
  (let [cursor (m/get-in root path)]
    (m/listen-to
      cursor :value
      (fn [[event-type data]]
        (if data
          (reset! store data)))))
  store)

(defn inc!
  [root path]
  (let [cursor (m/get-in root path)]
    (m/swap! cursor inc)))

(defn dec!
  [root path]
  (let [cursor (m/get-in root path)]
    (m/swap! cursor dec)))
