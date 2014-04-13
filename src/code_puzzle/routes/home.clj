(ns code-puzzle.routes.home
  (:require [compojure.core :refer :all]
            [ring.util.response :refer [response]]
            [clojure.data.json :as json]
            [code-puzzle.views.layout :as layout]
            [code-puzzle.repository :as repository]))

(defn key-word-fn [json]
  (json/read-str json
    :key-fn keyword))

(defn home []
  (layout/common-json (key-word-fn "{\"message\" : \"Hello, world!\"}")))

(defn csv []
  (layout/common-json (repository/runa-data)))

(defn psv []
   (layout/common-json (repository/merchant-data)))

(defroutes home-routes
  (GET "/runatic/report" [] (home))
  (GET "/runa" [] (csv))
  (GET "/merchant" [] (psv)))