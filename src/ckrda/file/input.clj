(ns ckrda.file.input)

(defn map-lines-in-file-eager
  [filename map_func]
  (with-open [rdr (clojure.java.io/reader filename)]
    (doall (map map_func (line-seq rdr)))))

(defn map-lines-in-file
  [filename map_func]
  (with-open [rdr (clojure.java.io/reader filename)]
    (doall (map map_func (line-seq rdr)))))
