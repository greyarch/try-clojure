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


(rum/defc sample-component < rum/static
  []
  (mdl/card
    {:mdl [:shadow--2dp] :style {:margin-top 30}}
    (mdl/card-title "This is a sample component")
    (mdl/card-text
      [:div
         {:style {:padding-top 30}}
         (mdl/checkbox
           {:mdl [:ripple], :for "checkbox-1", :input {:id "checkbox-1"}, :label "Checkbox"})
         (mdl/switch {:mdl [:ripple], :for "switch-2", :input {:id "switch-2"}} :title "Switch")])))