(ns gfycat-api.core-test
  (:require [clojure.test :refer [deftest is testing]]
            [gfycat-api.core :as core]
            ))

#_(deftest get-token-test
    (let [token (core/get-token)]
      (is (not (nil? token)))
      (is (= (:expires-in token) 3600))))
#_(def token (:access-token (core/get-token)))
#_(deftest search-test
    (testing "should return one gfycat search result"
      (let [search-result (core/search token "thanos power up" 1)]
        (is (= (:cursor search-result) ""))))
    (testing "searchnig with cursor"))
