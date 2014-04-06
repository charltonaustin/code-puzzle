(ns code-puzzle.views.layout
  (:require [hiccup.page :refer [html5 include-css]]
            [ring.util.response :refer [response content-type]]))


(defn common-json [content]
  (-> (response content)
    (content-type "application/json")))