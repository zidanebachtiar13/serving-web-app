(ns serving-web-app.dashboard
  (:require [hiccup.page :refer [html5 include-css]]
            [hiccup.element :refer [image link-to]]))

(defn category [subject]
  [:td
   [:div.card2
    (image (str "images/" subject ".png") subject)
    [:p
     "Score 2691" [:br]
     "Rank 215 dari semua peserta" [:br]
     "Lvl. 100 of 100"]
    (link-to (str "/" subject) [:button "Start"])]])

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
         [:div.logout (link-to "/logout" "Log out")]]]]]
     [:a.button {:href "/leaderboard"}
      [:table
       [:tr
        [:td [:i.gg-insights]]
        [:td.left "&nbsp&nbspLeaderboard"]]]]
     [:table
      [:tr
       (category "math")
       (category "vl")
       (category "english")
       (category "clojure")]]]))
