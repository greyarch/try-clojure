(ns try-clojure.ui
  (:require
    [rum.core :as rum]
    [rum.mdl  :as mdl]
    [markdown.core :refer [md->html]]))

(rum/defc markdown
  [md]
  [:span {:dangerouslySetInnerHTML {:__html (md->html md)}}])


(rum/defc counter-card < rum/static
  [title counter inc-fn dec-fn]
  (mdl/card
    {:mdl [:shadow--2dp]}
    (mdl/card-title title)
    (mdl/card-menu
      [ (mdl/button {:mdl [:icon :ripple :color--grey-200] :on-click dec-fn}
                    (mdl/icon "remove"))
        (mdl/button { :mdl [:icon :ripple :color--grey-200]
                      :style {:margin-left 5}
                      :on-click inc-fn}
                    (mdl/icon "add"))])
    (mdl/card-text [:h3 counter])
    (mdl/card-action
      {:mdl [:border]})
    (mdl/card-text
      (markdown
        "This is a simple counter backed by [Firebase](https://firebase.google.com).
Click the **plus** and **minus** buttons to change the counter."))))
