(ns code-puzzle.handler
  (:require [compojure.core :refer [defroutes routes]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.middleware.logger :refer [wrap-with-logger]]
            [code-puzzle.routes.home :refer [home-routes]]
            [ring.adapter.jetty :as jetty]
            [clojure.tools.logging :refer [info]]))

(defn init []
  (info "code-puzzle is starting"))

(defn destroy []
  (info "code-puzzle is shutting down"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (routes home-routes)
    (handler/site)
    (wrap-json-response)
    (wrap-base-url)
    (wrap-with-logger)))


(defn -main [port]
  (jetty/run-jetty app {:port (Integer. port) :join? false}))