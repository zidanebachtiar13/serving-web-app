(ns serving-web-app.problems)

(def math (->> "resources/public/problems/math.edn"
               (slurp)
               (read-string)))

(def vl (->> "resources/public/problems/vl.edn"
               (slurp)
               (read-string)))

(def english (->> "resources/public/problems/english.edn"
               (slurp)
               (read-string)))

(defn get-id [problem-id]
  (loop [index 0
         choice (cond
                  (= (apply str (take 3 problem-id)) "565") math
                  (= (apply str (take 4 problem-id)) "5699") vl
                  (= (apply str (take 4 problem-id)) "5690") english)] 
    (if (= (get-in choice [index :problem-id]) problem-id)
      (get choice index)
      (recur (inc index) choice))))

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

(defn check-jawaban [id jawaban]
  (let [soal (get-id id)]
    (if (nil? jawaban) 
      (swap! tak-terjawab inc)
      (if (= jawaban (get-in soal [:soal :jawaban])) 
        (swap! score inc)
        (swap! soal-salah inc)))))

(def subject (atom nil))

(defn change-subject [id]
  (let [choice (cond
                 (= (apply str (take 3 id)) "565") "/math"
                 (= (apply str (take 4 id)) "5699") "/vl"
                 (= (apply str (take 4 id)) "5690") "/english")] 
    (reset! subject choice)))
