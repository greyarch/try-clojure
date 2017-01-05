(ns try-rum.app
    (:require
      [rum.core   :as rum]
      [rum.mdl    :as mdl]
      [try-rum.db :as db]))

(def counts (db/sync (atom 0)))

(rum/defc counter < rum/reactive
  []
  [:div
    (mdl/button
      { :on-click db/increment
        :mdl [:fab :colored]}
      (mdl/icon "add"))
    [:h1 (rum/react counts)]])

(defn init []
  (rum/mount (counter) (. js/document (getElementById "container"))))
