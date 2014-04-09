(ns code-puzzle.repository-test
  (:use clojure.test
        code-puzzle.repository))

(def file-input "h1,h2,h3\n1,2,3\n4,5,6\n7,8,9")
(def double-seq (seq ["1.2" "1" "-3.2"]))
(def new-line-pattern (re-pattern ".*\n"))
(def elem-pattern (re-pattern "\\w+|\\d+,"))


(deftest repository-test
  (testing "break into lines"
    (let [actual (break-into-lines new-line-pattern file-input)]
      (is (= (first actual) "h1,h2,h3\n")))))

(deftest repository-test-1
  (testing "break into individual elements"
    (is (= (break-into-elem elem-pattern "h1,h2,h3\n") (seq ["h1" "h2" "h3"])))
    (is (= (break-into-elem elem-pattern "1,2,3\n") (seq ["1" "2" "3"])))
    (is (= (break-into-elem elem-pattern "7,8,9") (seq ["7" "8" "9"])))))

(deftest repository-test-2
  (testing "parse doubles"
    (is (= (parse-doubles double-seq) (seq [1.2 1.0 -3.2])))))
