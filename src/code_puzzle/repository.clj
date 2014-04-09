(ns code-puzzle.repository)

(defn break-into-lines [string]
  (re-seq (re-pattern ".*\n") string))

(defn get-csv []
  (slurp "resources/runa_data.csv"))

