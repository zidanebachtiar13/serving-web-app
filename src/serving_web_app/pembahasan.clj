(ns serving-web-app.pembahasan
  (:require [hiccup.page :refer [html5 include-css include-js]]
            [hiccup.element :refer [link-to]]))

(defn jawaban-benar [soal index pilihan]
 (if (get-in @soal [index :soal :options pilihan 0])
  [:p.jawaban-benar (get-in @soal [index :soal :options pilihan 1])]
  [:p.jawaban (get-in @soal [index :soal :options pilihan 1])])) 

(defn jawaban [soal index]
  (cond
    (nil? (get-in @soal [index :soal :options 2 1])) [:div.content
                                                      [:p "Pilihan Jawaban"]
                                                      (jawaban-benar soal index 0)
                                                      (jawaban-benar soal index 1)]
    (nil? (get-in @soal [index :soal :options 3 1])) [:div.content
                                                      [:p "Pilihan Jawaban"]
                                                      (jawaban-benar soal index 0)
                                                      (jawaban-benar soal index 1)
                                                      (jawaban-benar soal index 2)]
    (nil? (get-in @soal [index :soal :options 4 1])) [:div.content
                                                      [:p "Pilihan Jawaban"]
                                                      (jawaban-benar soal index 0)
                                                      (jawaban-benar soal index 1)
                                                      (jawaban-benar soal index 2)
                                                      (jawaban-benar soal index 3)]
    :else [:div.content
           [:p "Pilihan Jawaban"]
           (jawaban-benar soal index 0)
           (jawaban-benar soal index 1)
           (jawaban-benar soal index 2)
           (jawaban-benar soal index 3)
           (jawaban-benar soal index 4)]))

(defn base-page [soal]
  (html5
    [:head
     [:title "Pembahasan"]
     (include-css "css/pembahasan.css")]
    [:body
     [:div.navbar
      (link-to "/" [:i.gg-close-r])
      [:h2 "Pembahasan"]]
     [:div.tab
      (for [i (range 1 9)]
        (if (= i 1)
          [:button#defaultOpen.tablinks {:onClick (str "openPembahasan('no" i "')")} (str "Soal No. " i)]
          [:button.tablinks {:onClick (str "openPembahasan('no" i "')")} (str "Soal No. " i)]))]
     (for [i (range 1 9)]
       [:div.tabcontent {:id (str "no" i)}
        [:div.content
         [:p "Soal"]
         [:p (get-in @soal [(dec i) :soal :soal-text])]]
        (jawaban soal (dec i))
        [:div.content
         [:p "Pembahasan" [:br] [:br]
          (get-in @soal [(dec i) :bahas])]]])
     (include-js "js/script.js")]))
