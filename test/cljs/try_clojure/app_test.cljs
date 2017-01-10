(ns try-clojure.app-test
  (:require-macros [cljs.test :refer [deftest testing is]])
  (:require [cljs.test :as t]
            [try-clojure.app :as app]))

(deftest test-arithmetic []
  (is (= (+ 1 1) 2) "Something went wrong."))
