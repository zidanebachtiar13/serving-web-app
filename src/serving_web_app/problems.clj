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
    (if (= (:problem-id (get choice index)) problem-id)
      (get choice index)
      (recur (inc index) choice))))
