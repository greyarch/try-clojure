(ns try-clojure.ui
  (:require
    [rum.core :as rum]
    [rum.mdl  :as mdl]
    [markdown.core :refer [md->html]]))

(rum/defc markdown
  [md]
  [:span {:dangerouslySetInnerHTML {:__html (md->html md)}}])


(rum/defc card < rum/static
  [counter inc-fn]
  (mdl/card
    {:mdl [:shadow--2dp]}
    (mdl/card-title "Counter")
    (mdl/card-menu
      (mdl/button {:mdl [:icon :ripple :color--grey-200] :on-click inc-fn}
                  (mdl/icon "add")))
    (mdl/card-text [:h3 counter])
    (mdl/card-action
      {:mdl [:border]})
    (mdl/card-text
      (markdown
        "This is a simple counter backed by [Firebase](https://firebase.google.com). Click on the **plus** button to increment the counter."))))
