(ns cuid.core-test
  (:require [clojure.test :refer :all]
            [cuid.core :refer :all]))

(defmacro timed [expr]
  (let [sym (= (type expr) clojure.lang.Symbol)]
    `(let [start# (. System (nanoTime))
           return# ~expr
           res# (if ~sym
                    (resolve '~expr)
                    (resolve (first '~expr)))]
       (prn (str "Timed "
                 (:name (meta res#))
                 ": " (/ (double (- (. System (nanoTime))
                                    start#))
                         1000000.0)
                 " msecs"))
       return#)))

(defn collision-test [n f]
  (is (= n (count (into #{} (map #(%)) (repeat n f))))))

(deftest cuid-test
  (testing "Collision test."
    (timed (collision-test 1200000 cuid))))
