(ns serving-web-app.result
  (:require [hiccup.page :refer [html5]]
            [garden.core :refer [css]]))

(def style
  (css
    [:table {:width "100%"}]
    [:th {:width "75%"
          :font-size "150px"}]
    [:td {:text-align "center"}]))

(defn result-page [score]
  (html5
    [:head
     [:title "Result"]
     [:style style]]
    [:body
     [:table
      [:tr
       [:th {:rowspan "6"} (str "+" @score)]
       [:td @score [:br] "Soal Benar"]]
      [:tr
       [:td (- 8 @score) [:br] "Soal Salah"]]
      [:tr
       [:td "00:04" [:br] "Rata-Rata Waktu"]]]]))
