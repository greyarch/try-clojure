(ns try-rum.ui
  (:require
    [rum.core :as rum]
    [rum.mdl  :as mdl]))

(rum/defc card < rum/static
  [counter inc-fn]
  (mdl/card
    {:mdl [:shadow--2dp]}
    (mdl/card-title "Counter")
    (mdl/card-menu
      (mdl/button {:mdl [:icon :ripple ] :on-click inc-fn}
                  (mdl/icon "add")))
    (mdl/card-text [:h3 counter])
    (mdl/card-action
              {:mdl [:border]})
    (mdl/card-text [:strong "This is a simple counter backed by Firebase.
        Click on the plus button to increment the counter."])))
