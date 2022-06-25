(ns serving-web-app.dashboard
  (:require [hiccup.page :refer [html5 include-css]]
            [hiccup.element :refer [image]]))

(defn base-page [& body]
  (html5
    [:head
     [:title "Dashboard"]
     (include-css "css/dashboard.css")]
    [:body
     [:div.card
      [:table
       [:tr
        [:td.headText "Zidane Bachtiar"]
        [:td]]
       [:tr
        [:td "Total Score 1019 Points"]
        [:td]]
       [:tr
        [:td.lightText "Rank 100 dari semua peserta"]
        [:td
         [:a.logout {:href "/logout"} "Log out"]]]]]
     [:a.button {:href "/leaderboard"}
      [:table
       [:tr
        [:td [:i.gg-insights]]
        [:td.left "&nbsp&nbspLeaderboard"]]]]
     body]))

(defn category [math-id vl-id english-id]
  (base-page
    [:table
     [:tr
      [:td
       [:div.card2
        (image "images/math.png" "math")
        [:p 
         "Score 2691" [:br] 
         "Rank 215 dari semua peserta" [:br]
         "Lvl. 100 of 100"]
        [:a {:href (str "/math/" math-id)} [:button "Start"]]]]
      [:td
       [:div.card2
        (image "images/verbal_logic.png" "math")
        [:p 
         "Score 2691" [:br] 
         "Rank 215 dari semua peserta" [:br]
         "Lvl. 100 of 100"]
        [:a {:href (str "/vl/" vl-id)} [:button "Start"]]]]
      [:td
       [:div.card2
        (image "images/english.png" "math")
        [:p 
         "Score 2691" [:br] 
         "Rank 215 dari semua peserta" [:br]
         "Lvl. 100 of 100"]
        [:a {:href (str "/english/" english-id)} [:button "Start"]]]]
      [:td
       [:div.card2
        (image "images/clojure.png" "math")
        [:p 
         "Score 2691" [:br] 
         "Rank 215 dari semua peserta" [:br]
         "Lvl. 100 of 100"]
        [:a {:href "/content"} [:button "Start"]]]]]]))
