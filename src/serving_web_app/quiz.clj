(ns serving-web-app.quiz
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as form]
            [garden.core :refer [css]]
            [ring.util.anti-forgery :refer (anti-forgery-field)]))

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

(defn subject [problems index]
  (cond
    (= (apply str (take 3 (get-in problems [index :problem-id]))) "565") [:div
                                                                          [:p (get-in problems [index :soal :soal-text])]
                                                                          (form/hidden-field (str "no" index "-id") (get-in problems [index :problem-id]))
                                                                          (form/radio-button (str "no" index) false "A")
                                                                          (form/label (str "no" index) (get-in problems [index :soal :options 0 1]))
                                                                          (form/radio-button (str "no" index) false "B")
                                                                          (form/label (str "no" index) (get-in problems [index :soal :options 1 1]))
                                                                          [:br]
                                                                          (form/radio-button (str "no" index) false "C")
                                                                          (form/label (str "no" index) (get-in problems [index :soal :options 2 1]))
                                                                          (form/radio-button (str "no" index) false "D")
                                                                          (form/label (str "no" index) (get-in problems [index :soal :options 3 1]))
                                                                          [:br]
                                                                          (form/radio-button (str "no" index) false "E")
                                                                          (form/label (str "no" index) (get-in problems [index :soal :options 4 1]))]
    (= (apply str (take 4 (get-in problems [index :problem-id]))) "5699") [:div
                                                                           [:p (get-in problems [index :soal :soal-text])]
                                                                           (form/hidden-field (str "no" index "-id") (get-in problems [index :problem-id]))
                                                                           (form/radio-button (str "no" index) false "A")
                                                                           (form/label (str "no" index) (get-in problems [index :soal :options 0 1]))
                                                                           (form/radio-button (str "no" index) false "B")
                                                                           (form/label (str "no" index) (get-in problems [index :soal :options 1 1]))]
    (= (apply str (take 4 (get-in problems [index :problem-id]))) "5690") [:div
                                                                           [:p (get-in problems [index :soal :soal-text])]
                                                                           (form/hidden-field (str "no" index "-id") (get-in problems [index :problem-id]))
                                                                           (form/radio-button (str "no" index) false "A")
                                                                           (form/label (str "no" index) (get-in problems [index :soal :options 0 1]))
                                                                           (form/radio-button (str "no" index) false "B")
                                                                           (form/label (str "no" index) (get-in problems [index :soal :options 1 1]))
                                                                           [:br]
                                                                           (form/radio-button (str "no" index) false "C")
                                                                           (form/label (str "no" index) (get-in problems [index :soal :options 2 1]))
                                                                           (form/radio-button (str "no" index) false "D")
                                                                           (form/label (str "no" index) (get-in problems [index :soal :options 3 1]))]))


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
       (get (soal problems) 0)
       (get (soal problems) 1)
       (get (soal problems) 2)
       (get (soal problems) 3)
       (get (soal problems) 4)
       (get (soal problems) 5)
       (get (soal problems) 6)
       (get (soal problems) 7)
       (anti-forgery-field)
       (form/submit-button "submit"))]))

(defn math-quiz [problems]
  (base-quiz "Math Quiz" problems))

(defn vl-quiz [problems]
  (base-quiz "Verbal Logic Quiz" problems))

(defn english-quiz [problems]
  (base-quiz "English Quiz" problems))
