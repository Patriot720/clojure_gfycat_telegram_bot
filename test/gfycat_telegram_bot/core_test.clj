(ns gfycat-telegram-bot.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [morse.api :as t]
            [gfycat-telegram-bot.core :as core]))
(deftest respond-with-gifs-test
  (with-redefs [t/answer-inline (fn [& args] (println "NICE") "LOL")]
    (is (= (core/respond-with-gifs {:query "nice"}) "LOL"))
))
