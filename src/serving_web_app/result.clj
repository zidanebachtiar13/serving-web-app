(ns serving-web-app.result
  (:require [hiccup.page :refer [html5]]
            [garden.core :refer [css]]))

(def style
  (css
    [:table {:width "100%"}]
    [:th {:width "75%"
          :font-size "150px"}]
    [:td {:text-align "center"}]))

(def result-page
  (html5
    [:head
     [:title "Result"]
     [:style style]]
    [:body
     [:table
      [:tr
       [:th {:rowspan "6"} "+9"]
       [:td "7" [:br] "Soal Benar"]]
      [:tr
       [:td "1" [:br] "Soal Salah"]]
      [:tr
       [:td "00:04" [:br] "Rata-Rata Waktu"]]]]))
