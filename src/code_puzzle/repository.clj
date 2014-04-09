(ns code-puzzle.repository)

(defn create-pattern [pattern]
  (re-pattern pattern))

(defn break-into-lines [pattern, string]
  (re-seq pattern string))

(defn break-into-elem [pattern, string]
  (re-seq pattern string))

(defn parse-doubles [strings]
  (map #(Double/parseDouble %1) strings))

(defn get-csv []
  (slurp "resources/runa_data.csv"))

