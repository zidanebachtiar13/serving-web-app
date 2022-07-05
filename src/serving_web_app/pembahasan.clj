(ns serving-web-app.pembahasan
  (:require [hiccup.page :refer [html5]]
            [garden.core :refer [css]])
  (:use [com.reasonr.scriptjure :only (js)]))

(def style
  (css
    [:* {:box-sizing "border-box"}]
    [:body :html
     {:height "100%"
      :margin "0"
      :font-family "Arial"}]
    [:.tablink
     {:background-color "#555"
      :color "white"
      :float "left"
      :border "none"
      :outline "none"
      :cursor "pointer"
      :padding "14px 16px"
      :font-size "17px"
      :width "25%"}
     [:&:hover
      {:background-color "#777"}]]
    [:.tabcontent
     {:color "white"
      :display "none"
      :padding "100px 20px"
      :height "100%"}]
    [:#Home {:background-color "red"}]
    [:#News {:background-color "green"}]
    [:#Contact {:background-color "blue"}]
    [:#About {:background-color "orange"}]))

(def base-page
  (html5
    [:head
     [:title "Pembahasan"]
     [:style style]]
    [:body
     [:button.tablink {:onclick "openPage('Home', this, 'red')"} "Home"]
     [:button.tablink {:onclick "openPage('News', this, 'green')" :id "defaultOpen"} "News"]
     [:button.tablink {:onclick "openPage('Contact', this, 'blue')"} "Contact"]
     [:button.tablink {:onclick "openPage('About', this, 'orange')"} "About"]
     [:div.tabcontent {:id "Home"}
      [:h3 "Home"]
      [:p "Home is where the heart is.."]]
     [:div.tabcontent {:id "News"}
      [:h3 "News"]
      [:p "Some news this fine day!"]]
     [:div.tabcontent {:id "Contact"}
      [:h3 "Contact"]
      [:p "Get in touch, or swing by for a cup of coffe."]]
     [:div.tabcontent {:id "About"}
      [:h3 "About"]
      [:p "Who we are and what we do"]]]))
