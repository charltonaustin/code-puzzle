(ns code-puzzle.repository)

(def csv (slurp "resources/runa_data.csv"))
(def psv (slurp "resources/merchant_data.psv"))

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

(defn convert-dollars [map-with-cents diss-field assco-field]
  (def assoc-value (cents-to-dollars (diss-field map-with-cents)))
  (dissoc map-with-cents diss-field)
  (assoc map-with-cents assco-field assoc-value))

(defn create-map [from-file]
  (def head (first from-file))
  (def tail (rest from-file))
  (map #(zipmap head %1) tail))

(defn to-lower-case [elements]
  (map to-lower-case-line elements))

(defn remove-spaces [elements]
  (map remove-spaces-line elements))

(defn process-data [data-source]
  (->
    (break-into-lines data-source)
    (break-into-elems)
    (to-lower-case)
    (remove-spaces)
    (create-map)))

(defn merchant-data
  (process-data psv))

(defn runa-data
  (process-data csv))

