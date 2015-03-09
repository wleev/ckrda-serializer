(ns ckrda.parsers.usda_test
  (:require [clojure.test :refer :all]
            [ckrda.parsers.usda :refer :all]))

(deftest string-extraction-test
  (let [testresult "test"
        actualresult (extract-value "~test~")]
    (is (= testresult actualresult))))

(deftest empty-extraction-test
  (let [testresult ""
        actualresult (extract-value "~~")]
    (is (= testresult actualresult))))

(deftest values-extraction-test
  (let [testresult (list "test" "" 1337)
        actualresult (extract-values "~test~^~~^1337")]
    (is (= testresult actualresult))))

(deftest complex-values-extraction-test
  (let [testresult (list "Butter, 2% fat" "CHEESE/SOY (not)" 1337)
        actualresult (extract-values "~Butter, 2% fat~^~CHEESE/SOY (not)~^1337")]
    (is (= testresult actualresult))))
