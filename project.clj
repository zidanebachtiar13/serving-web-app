(defproject serving-web-app "0.1.0-SNAPSHOT"
  :description "Serving web app like Zencore by Zenius"
  :url "https://serving-web-app.herokuapp.com"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [hiccup "2.0.0-alpha2"]
                 [garden "1.3.10"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler serving-web-app.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})
