(ns serving-web-app.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [serving-web-app.dashboard :as dashboard]
            [serving-web-app.result :as result]
            [serving-web-app.problems :as problem]
            [serving-web-app.math :as math]))

(defroutes app-routes
  (GET "/" [] (dashboard/category (:problem-id (get (shuffle problem/math) 0)))) 
  (GET "/math/:math-id" [math-id] (math/math-quiz (problem/get-id math-id)))
  (GET "/result" [] result/result-page)
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
