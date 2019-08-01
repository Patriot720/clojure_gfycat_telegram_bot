(ns gfycat-api.util-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [gfycat-api.fixtures :as fixtures]
   [gfycat-api.util :as util]))

(deftest search-result-filtering
  (is (= (util/get-gfycats-with [:title] fixtures/single-gfycat-search-result)
         '({:title
            "Canadian Flag waving animated using MIR plug in after effects - free motion graphics"}))))
