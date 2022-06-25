(ns serving-web-app.problems)

(def math (->> "resources/public/problems/math.edn"
               (slurp)
               (read-string)))

(defn get-id [problem-id]
  (loop [index 0]
    (if (= (:problem-id (get math index)) problem-id)
      (get math index)
      (recur (inc index)))))
