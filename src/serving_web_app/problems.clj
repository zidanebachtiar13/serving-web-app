(ns serving-web-app.problems
  (:require [clojure.string :as str]))

(defn get-soal [file awal akhir]
  (->> (-> file 
           (subvec awal akhir))
       (apply str)
       (read-string)))

(def math-Michael (->> "resources/public/problems/SoalMath.edn"
                            (slurp)
                            (str/split-lines)))

(def math1-Michael (get-soal math-Michael 0 80)) 

(def math2-Michael (get-soal math-Michael 81 171))

(def math3-Michael (get-soal math-Michael 172 252))

(def vl-Michael (->> "resources/public/problems/Soalverballogic.edn"
                     (slurp)
                     (str/split-lines)))

(def vl1-Michael (get-soal vl-Michael 0 80))

(def vl2-Michael (get-soal vl-Michael 81 221))

(def english-Michael (->> "resources/public/problems/SoalEnglish.edn"
                          (slurp)
                          (str/split-lines)))

(def english1-Michael (get-soal english-Michael 0 83))

(def english2-Michael (get-soal english-Michael 84 166))

(def english3-Michael (get-soal english-Michael 167 252))

(def math-Salma (->> "resources/public/problems/math.edn"
                     (slurp)
                     (read-string)))

(def vl-Salma (->> "resources/public/problems/verbal.edn"
                   (slurp)
                   (read-string)))

(def english-Salma (->> "resources/public/problems/english.edn"
                        (slurp)
                        (read-string)))

(def math (->> [math1-Michael math2-Michael math3-Michael math-Salma]
               (flatten)
               (vec)))

(def vl (->> [vl1-Michael vl2-Michael vl-Salma]
             (flatten)
             (vec)))

(def english (->> [english1-Michael english2-Michael english3-Michael english-Salma]
                  (flatten)
                  (vec)))

(defn take-soal [problem]
  (subvec problem 0 8))

(def score (atom 0))

(defn reset-score []
  (reset! score 0))

(def soal-salah (atom 0))

(defn reset-soal-salah []
  (reset! soal-salah 0))

(def tak-terjawab (atom 0))

(defn reset-tak-terjawab []
  (reset! tak-terjawab 0))

(defn get-soal-by-id [topic problem-id]
  (loop [index 0
         choice (cond
                  (= topic "math") math
                  (= topic "verbal-logic") vl
                  (= topic "english") english)]
    (if (= (get-in choice [index :problem-id]) problem-id)
      (get choice index)
      (recur (inc index) choice))))

(defn check-jawaban [topic id jawaban]
  (let [soal (get-soal-by-id topic id)]
    (if (nil? jawaban) 
      (swap! tak-terjawab inc)
      (if (= jawaban (get-in soal [:soal :jawaban])) 
        (swap! score inc)
        (swap! soal-salah inc)))))

(def subject (atom nil))

(defn change-subject [topic]
  (let [choice (cond
                 (= topic "math") "/math"
                 (= topic "verbal-logic") "/vl"
                 (= topic "english") "/english")] 
    (reset! subject choice)))

(def soal (atom nil))

(defn save-soal [problems]
  (reset! soal (read-string (str "[" problems "]"))))
