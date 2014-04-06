(ns code-puzzle.routes.home
  (:require [compojure.core :refer :all]
            [ring.util.response :refer [response]]
            [clojure.data.json :as json]
            [code-puzzle.views.layout :as layout]))

(defn key-word-fn [json]
  (json/read-str json
    :key-fn keyword))

(defn home []
  (layout/common-json (key-word-fn "{\"message\" : \"Hello, world!\"}")))

(defroutes home-routes
  (GET "/runatic/report" [] (home)))