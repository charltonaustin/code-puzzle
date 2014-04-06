(ns code-puzzle.handler-test
  (:require [clojure.data.json :as json])
  (:use clojure.test
        ring.mock.request
        code-puzzle.handler))

(defn get-url [url]
  (app (request :get url)))

(defn extract-body [response]
  (json/read-str (:body response) :key-fn keyword))

(deftest code-puzzle-app
  (testing "GET /runatic/report"
    (let [response (get-url "/runatic/report")]
      (is (= (:status response) 200))
      (is (= (:message (extract-body response) "Hello, world!"))))))

