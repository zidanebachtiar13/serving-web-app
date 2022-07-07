(ns serving-web-app.pembahasan
  (:require [hiccup.page :refer [html5 include-css]]
            [hiccup.element :refer [link-to]]
            [garden.core :refer [css]]))

(def base-page
  (html5
    [:head
     [:title "Pembahasan"]
     (include-css "css/pembahasan.css")]
    [:body
     [:div.navbar
      (link-to "/" [:i.gg-close-r])
      [:h2 "Pembahasan"]]]))
