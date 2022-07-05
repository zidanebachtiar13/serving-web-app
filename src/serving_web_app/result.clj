(ns serving-web-app.result
  (:require [hiccup.page :refer [html5]]
            [hiccup.element :refer [link-to]]
            [garden.core :refer [css]]))

(def style
  (css
    [:table {:width "100%"}]
    [:th 
     {:width "75%"
      :font-size "130px"
      :border "1px solid black"
      :border-radius "5px"
      :padding "100px"}]
    [:td 
     {:text-align "center"
      :border "1px solid black"
      :border-radius "5px"}]
     [:.absolute 
      {:position "absolute"
       :top "320px"
       :left "500px"}
      [:p {:font-size "19px"}]]
     [:button 
      {:border-radius "12px"
       :text-align "center"
       :cursor "pointer"}]
     [:.button1
      {:width "60%"
       :font-size "17px"
       :margin-top "20px"
       :padding "20px"}]
     [:.button2
      {:position "absolute"
       :top "439px"
       :right "8px"
       :width "39%"
       :font-size "17px"
       :padding "60px"}]))

(defn accuracy [score]
  (let [acc (* (/ @score 8) 100)]
    (if (ratio? acc)
      (-> acc
          (float)
          (int)
          (str "%"))
      (str acc "%"))))

(defn result-page [score subject]
  (html5
    [:head
     [:title "Result"]
     [:style style]]
    [:body
     [:div.absolute
      [:p "Score"]]
     [:table
      [:tr
       [:th {:rowspan "4"} (str "+" @score)]
       [:td @score [:br] "Soal Benar"]]
      [:tr
       [:td (- 8 @score) [:br] "Soal Salah"]]
      [:tr
       [:td "00:04" [:br] "Rata-rata Waktu"]]
      [:tr
       [:td (accuracy score) [:br] "Tingkat Akurasi"]]]
     (link-to "/" [:button.button2 "Udahan"])
     [:br]
     (link-to @subject [:button.button1 "Main Lagi"])
     [:br]
     (link-to "/pembahasan" [:button.button1 "Pembahasan"])]))
