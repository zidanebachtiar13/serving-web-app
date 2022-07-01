(ns serving-web-app.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.response :as resp]
            [serving-web-app.dashboard :as dashboard]
            [serving-web-app.result :as result]
            [serving-web-app.problems :as problem]
            [serving-web-app.math :as math]
            [serving-web-app.vl :as vl]
            [serving-web-app.english :as english]))

(defroutes app-routes
  (GET "/" [] (dashboard/category (:problem-id (get (shuffle problem/vl) 0)) (:problem-id (get (shuffle problem/english) 0)))) 
  (GET "/math" [] (math/math-quiz (problem/take-soal (shuffle problem/math))))
  (GET "/vl/:vl-id" [vl-id] (vl/vl-quiz (problem/get-id vl-id)))
  (GET "/english/:english-id" [english-id] (english/english-quiz (problem/get-id english-id)))
  (POST "/quiz" [no0-id no0 no1-id no1 no2-id no2 no3-id no3 no4-id no4 no5-id no5 no6-id no6 no7-id no7] (do (problem/reset-score)
                                                                                                              (problem/check no0-id no0)
                                                                                                              (problem/check no1-id no1)
                                                                                                              (problem/check no2-id no2)
                                                                                                              (problem/check no3-id no3)
                                                                                                              (problem/check no4-id no4)
                                                                                                              (problem/check no5-id no5)
                                                                                                              (problem/check no6-id no6)
                                                                                                              (problem/check no7-id no7)
                                                                                                              (resp/redirect "/result"))) 
  (GET "/result" [] (result/result-page problem/score))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
