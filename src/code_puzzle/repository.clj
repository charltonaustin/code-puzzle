(ns code-puzzle.repository)

(defn break-into-lines [string]
  (re-seq (re-pattern "[a-zA-Z_0-9 ,|]+\n") string))

(defn break-into-elem [string]
  (re-seq (re-pattern "[a-zA-Z_0-9 \t]+") string))

(defn parse-big-dec [strings]
  (map bigdec strings))

(defn dollars-to-cents [dollars]
  (map #(* 100M %1) dollars))

(defn break-into-elems [lines]
  (map break-into-elem lines))

(defn get-csv []
  (let [csv (slurp "resources/runa_data.csv")]
    (->
      (break-into-lines csv)
      (break-into-elems))))

(defn get-psv []
  (let [csv (slurp "resources/merchant_data.psv")]
    (->
      (break-into-lines csv)
      (break-into-elems))))

