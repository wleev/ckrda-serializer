(ns ckrda.file.input_test
  (:require [clojure.test :refer :all]
            [ckrda.file.input :refer :all]))

(deftest do-for-all-lines-test
  (let [test_res [ "TEST" "1" "2" "3"]
        actual_res (map-lines-in-file-eager
                    (.getPath (clojure.java.io/resource "test/file/test_file.txt"))
                    identity)]
    (println actual_res)
    (is (= test_res actual_res))))
