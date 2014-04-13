(ns code-puzzle.repository)

(defn break-into-lines [string]
  (re-seq (re-pattern "[a-zA-Z_0-9 ,|.]+\n") string))

(defn break-into-elem [string]
  (re-seq (re-pattern "[a-zA-Z_0-9 \t.]+") string))

(defn parse-big-dec [strings]
  (map bigdec strings))

(defn to-lower-case-line [strings]
  (map #(.toLowerCase %1) strings))

(defn remove-spaces-line [strings]
  (map #(.replace %1 " " "-") strings))

(defn cents-to-dollars [amount]
  (.divide amount 100M))

(defn break-into-elems [lines]
  (map break-into-elem lines))

(defn create-map [something]
  (def head (first something))
  (def tail (rest something))
  (map #(zipmap head %1) tail))

(defn to-lower-case [elements]
  (map to-lower-case-line elements))

(defn remove-spaces [elements]
  (map remove-spaces-line elements))

(defn runa-data []
  (let [csv (slurp "resources/runa_data.csv")]
    (->
      (break-into-lines csv)
      (break-into-elems)
      (to-lower-case)
      (remove-spaces)
      (create-map))))

(defn merchant-data []
  (let [psv (slurp "resources/merchant_data.psv")]
    (->
      (break-into-lines psv)
      (break-into-elems)
      (to-lower-case)
      (remove-spaces)
      (create-map))))

