(ns code-puzzle.repository-test
  (:use clojure.test
        code-puzzle.repository))

(def file-input "h1,h2,h3\n1,2,3\n")
(def file-output (seq [(seq ["h1" "h2" "h3"]) (seq ["1" "2" "3"])]))
(def double-seq (seq ["1.2" "1" "-3.2"]))
(def cents (seq [1200M -30M 123M]))


(deftest test-break-into-lines
  (testing "break into lines"
    (let [actual (break-into-lines file-input)]
      (is (= (first actual) "h1,h2,h3\n")))))

(deftest test-break-into-elem
  (testing "break into individual elements"
    (is (= (break-into-elem "h1,h2,h3\n") (seq ["h1" "h2" "h3"])))
    (is (= (break-into-elem "1,2,3\n") (seq ["1" "2" "3"])))
    (is (= (break-into-elem "1|2|3\n") (seq ["1" "2" "3"])))
    (is (= (break-into-elem "First Column|Second Column|Third\n")
          (seq ["First Column" "Second Column" "Third"])))
    (is (= (break-into-elem "7,8,9") (seq ["7" "8" "9"])))))

(deftest test-parse-doubles
  (testing "parse doubles"
    (is (= (parse-big-dec double-seq) (seq [1.2M 1M -3.2M])))))

(deftest test-cents-to-dollars
  (testing "cents to dollars")
  (is (= (map cents-to-dollars cents) (seq [12M -0.3M 1.23M]))))

(deftest test-break-into-elems
  (testing "break multiple lines into elems"
    (is (= (break-into-elems (break-into-lines file-input)) file-output))))

(deftest test-to-lower-case
  (testing "turn strings to lower case"
    (is (= (to-lower-case-line (seq ["FirST234" "SEC OND"])) (seq ["first234" "sec ond"]))))
  (testing "remove spaces from strings"
    (is (= (remove-spaces-line (seq ["cats and dogs" "kittens rock!"])) (seq ["cats-and-dogs" "kittens-rock!"])))))
