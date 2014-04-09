(ns code-puzzle.repository-test
  (:use clojure.test
        code-puzzle.repository))

(def file-input "h1,h2,h3\n1,2,3\n4,5,6\n7,8,9")


(deftest repository-test
  (testing "break into lines"
    (let [actual (break-into-lines file-input)]
      (is (= (first actual) "h1,h2,h3\n")))))
