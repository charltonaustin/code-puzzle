(defproject code-puzzle "0.1.0-SNAPSHOT"
  :uberjar-name "code-puzzle.jar"
  :description "Solution to Runa Code Puzzle"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.6"]
                 [hiccup "1.0.5"]
                 [ring-server "0.3.1"]
                 [ring/ring-json "0.3.0"]
                 [org.clojure/data.json "0.2.4"]
                 [ring.middleware.logger "0.4.0"]
                 [log4j "1.2.17"]]
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler code-puzzle.handler/app
         :init code-puzzle.handler/init
         :destroy code-puzzle.handler/destroy
         :port 3001}

  :aot :all
  :profiles
  {:production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.2.1"]]}})
