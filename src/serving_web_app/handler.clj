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
  (POST "/quiz" [problems topic no0-id no0 no1-id no1 no2-id no2 no3-id no3 no4-id no4 no5-id no5 no6-id no6 no7-id no7] (do (problem/reset-score)
                                                                                                                             (problem/reset-soal-salah)
                                                                                                                             (problem/reset-tak-terjawab)
                                                                                                                             (problem/reset-jawaban)
                                                                                                                             (problem/change-subject topic)
                                                                                                                             (problem/save-soal problems)
                                                                                                                             (problem/save-jawaban no0 no1 no2 no3 no4 no5 no6 no7)
                                                                                                                             (problem/check-jawaban topic no0-id no0)
                                                                                                                             (problem/check-jawaban topic no1-id no1)
                                                                                                                             (problem/check-jawaban topic no2-id no2)
                                                                                                                             (problem/check-jawaban topic no3-id no3)
                                                                                                                             (problem/check-jawaban topic no4-id no4)
                                                                                                                             (problem/check-jawaban topic no5-id no5)
                                                                                                                             (problem/check-jawaban topic no6-id no6)
                                                                                                                             (problem/check-jawaban topic no7-id no7)
                                                                                                                             (resp/redirect "/result"))) 
  (GET "/result" [] (result/result-page problem/score problem/subject problem/soal-salah problem/tak-terjawab))
  (GET "/pembahasan" [] (pembahasan/base-page problem/soal problem/jawaban))
  (route/not-found "Kontennya belum ada nih"))

(def app
  (wrap-defaults app-routes site-defaults))
