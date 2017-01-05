(ns try-rum.app
  (:require
    [rum.core   :as rum]
    [rum.mdl    :as mdl]
    [try-rum.db :as db]
    [try-rum.ui :as ui]))

(def counts (db/sync (atom 0)))

(rum/defc app < rum/reactive
  []
  [:div {:style {:padding 30}}
   (ui/card (rum/react counts) db/increment)])


(defn init []
  (rum/mount (app) (. js/document (getElementById "container"))))
