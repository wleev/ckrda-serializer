(ns ckrda.mongodb.serializer
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [monger.conversion  :refer :all])
  (:import [com.mongodb MongoOptions ServerAddress]
           [org.bson.types ObjectId]))

(let [conn (mg/connect)
      db   (mg/get-db conn "ckrda")]
  
  ;; ensure indexes on all our collections
  ;; food descriptions collection
  (mc/ensure-index db "food_desc" (array-map :ndb_nr 1) { :unique true :name "by-ndb-nr" })
  (mc/ensure-index db "food_desc" (array-map :foodgroup_code 1) { :name "by-foodgroup-code" })
  ;; food group descriptions collection
  (mc/ensure-index db "food_group_desc" (array-map :foodgroup_code 1) { :unique true :name "by-foodgroup-code" })
  ;; langual collection
  (mc/ensure-index db "langual" (array-map :ndb_nr 1 :factor_code 1) 
                   { :unique true, :name "by-ndb-langual-code" })
  ;; langual factors collection
  (mc/ensure-index db "langual_desc" (array-map :factor_code 1) { :unique true :name "by-langual-code" })
  ;; nutrient data collection
  (mc/ensure-index db "nutrient_data" (array-map :ndb_nr 1 :nutrient_code) { :unique true :name "by-ndb-nutrient-code" })
  ;; nutrient definitions collection
  (mc/ensure-index db "nutrient_defs" (array-map :nutrient_code 1) { :unique true :name "by-nutrient-code" })
  ;; weight collection
  (mc/ensure-index db "weight_defs" (array-map :ndb_nr 1) { :name "by-ndb-nr" })

  (defn mongo-insert
    [table, doc]
    (let [oid  (ObjectId.)]
      (mc/insert db table (to-db-object (merge doc {:id oid})))))
  )
  

