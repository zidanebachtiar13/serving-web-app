(ns serving-web-app.pembahasan
  (:require [hiccup.page :refer [html5 include-css include-js]]
            [hiccup.element :refer [link-to]]))

(defn jawaban-benar-salah [soal kumpulan-jawaban index pilihan]
 (cond 
   (get-in @soal [index :soal :options pilihan 0]) [:p.jawaban-benar (get-in @soal [index :soal :options pilihan 1])]
   (= (get @kumpulan-jawaban index) (get-in @soal [index :soal :options pilihan 2])) [:p.jawaban-salah (get-in @soal [index :soal :options pilihan 1])]
   :else [:p.jawaban (get-in @soal [index :soal :options pilihan 1])])) 

(defn jawaban [soal kumpulan-jawaban index]
  (cond
    (nil? (get-in @soal [index :soal :options 2 1])) [:div.content
                                                      [:p "Pilihan Jawaban"]
                                                      (jawaban-benar-salah soal kumpulan-jawaban index 0)
                                                      (jawaban-benar-salah soal kumpulan-jawaban index 1)]
    (nil? (get-in @soal [index :soal :options 3 1])) [:div.content
                                                      [:p "Pilihan Jawaban"]
                                                      (jawaban-benar-salah soal kumpulan-jawaban index 0)
                                                      (jawaban-benar-salah soal kumpulan-jawaban index 1)
                                                      (jawaban-benar-salah soal kumpulan-jawaban index 2)]
    (nil? (get-in @soal [index :soal :options 4 1])) [:div.content
                                                      [:p "Pilihan Jawaban"]
                                                      (jawaban-benar-salah soal kumpulan-jawaban index 0)
                                                      (jawaban-benar-salah soal kumpulan-jawaban index 1)
                                                      (jawaban-benar-salah soal kumpulan-jawaban index 2)
                                                      (jawaban-benar-salah soal kumpulan-jawaban index 3)]
    :else [:div.content
           [:p "Pilihan Jawaban"]
           (jawaban-benar-salah soal kumpulan-jawaban index 0)
           (jawaban-benar-salah soal kumpulan-jawaban index 1)
           (jawaban-benar-salah soal kumpulan-jawaban index 2)
           (jawaban-benar-salah soal kumpulan-jawaban index 3)
           (jawaban-benar-salah soal kumpulan-jawaban index 4)]))

(defn base-page [soal kumpulan-jawaban]
  (html5
    [:head
     [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
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
        (jawaban soal kumpulan-jawaban (dec i))
        [:div.content
         [:p "Pembahasan" [:br] [:br]
          (get-in @soal [(dec i) :bahas])]]])
     (include-js "js/script.js")]))
