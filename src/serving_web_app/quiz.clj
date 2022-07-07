(ns serving-web-app.quiz
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as form]
            [garden.core :refer [css]]
            [ring.util.anti-forgery :refer (anti-forgery-field)]))

(def style
  (css
    [:* {:box-sizing "border-box"}]
    [:.container 
     {:border "1px solid grey"
      :border-radius "5px"
      :margin "20px 0 30px 280px"
      :width "60%"
      :padding "0 10px 10px 10px"}]
    ["input[type=radio]" {:margin-bottom "8px"}]
    ["input[type=submit]" 
     {:margin "0 0 20px 1017px"
      :background-color "white"
      :border-radius "5px"
      :padding "12px"
      :font-size "15px"
      :cursor "pointer"}
     [:&:hover
      {:background-color "#F5F5DC"}]]))

(defn subject [problems index]
  (cond
    (nil? (get-in problems [index :soal :options 2 1])) [:div.container
                                                         [:p (str (inc index) "/8")]
                                                         [:p (get-in problems [index :soal :soal-text])]
                                                         (form/hidden-field (str "no" index "-id") (get-in problems [index :problem-id]))
                                                         (form/radio-button (str "no" index) false "A")
                                                         (form/label (str "no" index) (str "   " (get-in problems [index :soal :options 0 1])))
                                                         [:br]
                                                         (form/radio-button (str "no" index) false "B")
                                                         (form/label (str "no" index) (str "   " (get-in problems [index :soal :options 1 1])))]
    (nil? (get-in problems [index :soal :options 3 1])) [:div.container
                                                         [:p (str (inc index) "/8")]
                                                         [:p (get-in problems [index :soal :soal-text])]
                                                         (form/hidden-field (str "no" index "-id") (get-in problems [index :problem-id]))
                                                         (form/radio-button (str "no" index) false "A")
                                                         (form/label (str "no" index) (str "   " (get-in problems [index :soal :options 0 1])))
                                                         [:br]
                                                         (form/radio-button (str "no" index) false "B")
                                                         (form/label (str "no" index) (str "   " (get-in problems [index :soal :options 1 1])))
                                                         [:br]
                                                         (form/radio-button (str "no" index) false "C")
                                                         (form/label (str "no" index) (str "   " (get-in problems [index :soal :options 2 1])))]
    (nil? (get-in problems [index :soal :options 4 1])) [:div.container
                                                         [:p (str (inc index) "/8")]
                                                         [:p (get-in problems [index :soal :soal-text])]
                                                         (form/hidden-field (str "no" index "-id") (get-in problems [index :problem-id]))
                                                         (form/radio-button (str "no" index) false "A")
                                                         (form/label (str "no" index) (str "   " (get-in problems [index :soal :options 0 1])))
                                                         [:br]
                                                         (form/radio-button (str "no" index) false "B")
                                                         (form/label (str "no" index) (str "   " (get-in problems [index :soal :options 1 1])))
                                                         [:br]
                                                         (form/radio-button (str "no" index) false "C")
                                                         (form/label (str "no" index) (str "   " (get-in problems [index :soal :options 2 1])))
                                                         [:br]
                                                         (form/radio-button (str "no" index) false "D")
                                                         (form/label (str "no" index) (str "   " (get-in problems [index :soal :options 3 1])))]
    :else [:div.container
           [:p (str (inc index) "/8")]
           [:p (get-in problems [index :soal :soal-text])]
           (form/hidden-field (str "no" index "-id") (get-in problems [index :problem-id]))
           (form/radio-button (str "no" index) false "A")
           (form/label (str "no" index) (str "   " (get-in problems [index :soal :options 0 1])))
           [:br]
           (form/radio-button (str "no" index) false "B")
           (form/label (str "no" index) (str "   " (get-in problems [index :soal :options 1 1])))
           [:br]
           (form/radio-button (str "no" index) false "C")
           (form/label (str "no" index) (str "   " (get-in problems [index :soal :options 2 1])))
           [:br]
           (form/radio-button (str "no" index) false "D")
           (form/label (str "no" index) (str "   " (get-in problems [index :soal :options 3 1])))
           [:br]
           (form/radio-button (str "no" index) false "E")
           (form/label (str "no" index) (str "   " (get-in problems [index :soal :options 4 1])))]))

(defn soal [problems]
  (loop [items [] index 0]
    (if (= index (count problems))
      items
      (recur (conj items (subject problems index))
             (inc index)))))

(defn base-quiz [title problems]
  (html5
    [:head
     [:title title]
     [:style style]]
    [:body
     (form/form-to
       [:post "/quiz"]
       (for [i (range 8)]
         (get (soal problems) i))
       (anti-forgery-field)
       (form/submit-button "Submit"))]))

(defn math-quiz [problems]
  (base-quiz "Math Quiz" problems))

(defn vl-quiz [problems]
  (base-quiz "Verbal Logic Quiz" problems))

(defn english-quiz [problems]
  (base-quiz "English Quiz" problems))
