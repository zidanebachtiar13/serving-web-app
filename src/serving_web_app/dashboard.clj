(ns serving-web-app.dashboard
  (:require [hiccup.page :refer [html5 include-css]]
            [hiccup.element :refer [image link-to]]))

(defn category [subject]
  [:td
   [:div.card2
    (image (str "images/" subject ".png") subject)
    [:p
     "Score 0" [:br]
     "Rank 0 dari semua peserta" [:br]
     "Lvl. 0 of 100"]
    (link-to (str "/" subject) [:button "Start"])]])

(defn base-page [& body]
  (html5
    [:head
     [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
     [:title "Dashboard"]
     (include-css "css/dashboard.css")]
    [:body
     [:div.card
      [:table
       [:tr
        [:td.headText "Full Name"]
        [:td]]
       [:tr
        [:td "Total Score 0 Points"]
        [:td]]
       [:tr
        [:td.lightText "Rank 0 dari semua peserta"]
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
