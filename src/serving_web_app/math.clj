(ns serving-web-app.math
  (:require [hiccup.page :refer [html5]]
            [garden.core :refer [css]]))

(def style
  (css
    [:* {:box-sizing "border-box"}]
    [:p {:margin-left "10px"}]
    [:table {:width "100%"}]
    [:.jawaban {:margin "10px 3px"
                :border "1px solid black"
                :border-radius "5px"
                :padding "15px"}]
    [:button {:margin-top "230px"
              :position "absolute"
              :right "20px"
              :background-color "white"
              :border-radius "5px"
              :padding "12px"
              :text-decoration "none"
              :display "inline-block"
              :font-size "15px"
              :cursor "pointer"}]))   
  

(defn math-quiz [problem]
  (html5
    [:head
     [:title "Math Quiz"]
     [:style style]]
    [:body
     [:p (get-in problem [:soal :soal-text])]
     [:table
      [:tr
       [:td [:p.jawaban [:b "A "] (get-in problem [:soal :options 0 1])]]
       [:td [:p.jawaban [:b "C "] (get-in problem [:soal :options 2 1])]]]
      [:tr
       [:td [:p.jawaban [:b "B "] (get-in problem [:soal :options 1 1])]]
       [:td [:p.jawaban [:b "D "] (get-in problem [:soal :options 3 1])]]]
      [:tr
       [:td [:p.jawaban [:b "E "] (get-in problem [:soal :options 4 1])]]]]
     [:a {:href "/result"} [:button "Udahan!"]]]))
