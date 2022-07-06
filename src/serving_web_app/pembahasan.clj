(ns serving-web-app.pembahasan
  (:require [hiccup.page :refer [html5]]
            [garden.core :refer [css]]))

(def style
  (css
    [:body 
     {:background-color "#DCDCDC"
      :margin "0"}]
    [:.navbar 
     {:background-color "white"
      :overflow "hidden"
      :top "0"
      :width "100%"}]))

(def base-page
  (html5
    [:head
     [:title "Pembahasan"]
     [:style style]]
    [:body
     [:div.navbar
      [:h1 "Pembahasan"]]]))
