(ns serving-web-app.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [serving-web-app.dashboard :as dashboard]
            [serving-web-app.result :as result]
            [serving-web-app.problems :as problem]
            [serving-web-app.math :as math]
            [serving-web-app.vl :as vl]
            [serving-web-app.english :as english]))

(defroutes app-routes
  (GET "/" [] (dashboard/category (:problem-id (get (shuffle problem/math) 0)) (:problem-id (get (shuffle problem/vl) 0)) (:problem-id (get (shuffle problem/english) 0)))) 
  (GET "/math/:math-id" [math-id] (math/math-quiz (problem/get-id math-id)))
  (GET "/vl/:vl-id" [vl-id] (vl/vl-quiz (problem/get-id vl-id)))
  (GET "/english/:english-id" [english-id] (english/english-quiz (problem/get-id english-id)))
  (GET "/result" [] result/result-page)
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
