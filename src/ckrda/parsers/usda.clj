(ns ckrda.parsers.usda)
(require '[clojure.string :as str]
         '[clojure.edn :as edn]
         '[ckrda.file.input :as io])
  

(defn extract-value
  [raw]
  (let [trystring (re-find #"~([^~]*)~" raw)]
    (if trystring
      (last trystring)
      (edn/read-string raw))))

(defn extract-values
  [line]
  (map extract-value
       (str/split line #"\^")))
  
(defn parse-usda-food-desc
  [dir]
  (let [fdscr_f (str/join "/" [dir "FOOD_DES.txt"])
        fields [:ndb_nr :foodgroup_code :long_desc :short_desc :common_name :manufac_name :survey 
                :refuse_desc :refuse_pct :sci_name :n_factor :pro_factor :fat_factor :cho_factor]]
    (io/map-lines-in-file 
     fdscr_f 
     (fn [line] (zipmap fields (extract-values line))))))
