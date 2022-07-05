(ns serving-web-app.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.response :as resp]
            [serving-web-app.dashboard :as dashboard]
            [serving-web-app.result :as result]
            [serving-web-app.problems :as problem]
            [serving-web-app.quiz :as quiz]
            [serving-web-app.pembahasan :as pembahasan]))

(defroutes app-routes
  (GET "/" [] dashboard/base-page) 
  (GET "/math" [] (quiz/math-quiz (problem/take-soal (shuffle problem/math))))
  (GET "/vl" [] (quiz/vl-quiz (problem/take-soal (shuffle problem/vl))))
  (GET "/english" [] (quiz/english-quiz (problem/take-soal (shuffle problem/english))))
  (POST "/quiz" [no0-id no0 no1-id no1 no2-id no2 no3-id no3 no4-id no4 no5-id no5 no6-id no6 no7-id no7] (do (problem/reset-score)
                                                                                                              (problem/change-subject no0-id)
                                                                                                              (problem/check-jawaban no0-id no0)
                                                                                                              (problem/check-jawaban no1-id no1)
                                                                                                              (problem/check-jawaban no2-id no2)
                                                                                                              (problem/check-jawaban no3-id no3)
                                                                                                              (problem/check-jawaban no4-id no4)
                                                                                                              (problem/check-jawaban no5-id no5)
                                                                                                              (problem/check-jawaban no6-id no6)
                                                                                                              (problem/check-jawaban no7-id no7)
                                                                                                              (resp/redirect "/result"))) 
  (GET "/result" [] (result/result-page problem/score problem/subject))
  (GET "/pembahasan" [] pembahasan/base-page)
  (route/not-found "Kontennya belum ada nih"))

(def app
  (wrap-defaults app-routes site-defaults))
