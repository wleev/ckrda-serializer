(ns ckrda.core
  (:gen-class))

(require '[ckrda.file.input :as io]
         '[ckrda.mongodb.serializer :as mongs]
         '[ckrda.parsers.usda :as usda])

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (doall (map 
          (fn [entry] (mongs/mongo-insert "food_desc" entry))
          (usda/parse-usda-food-desc (first args)))))
    ;(println entry)))
    ;(mongs/mongo-insert "food-desc" entry)))

