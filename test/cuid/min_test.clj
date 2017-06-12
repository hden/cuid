(ns cuid.min-test
  (:require [clojure.test :refer :all]
            [cuid.min :refer :all]))

(deftest cuid-test
  (testing "Constant result can be obtained for testing by replacing all random functions"
    (is (= "cj3n55xem0000000000000000" (cuid {:timestamp    (constantly 1496848861534)
                                              :counter      (constantly 0)
                                              :fingerprint  (constantly "")
                                              :random-block (constantly "")})))))
