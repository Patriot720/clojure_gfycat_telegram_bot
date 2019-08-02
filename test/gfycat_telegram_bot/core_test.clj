(ns gfycat-telegram-bot.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [gfycat-telegram-bot.core :as core]))

(deftest to-telegram-array-test
  (is (= (core/to-telegram-array [{:title "Title"
                                   :gfyId 21
                                   :max1mbGif "fafa.gif"
                                   :gif100px "fsdjflkjfd.gif"}
                                  {:title "Title"
                                   :max1mbGif "fafa.gif"
                                   :gfyId 21
                                   :gif100px "fsdjflkjfd.gif"}])
         [{:type "gif"
           :title "Title"
           :id 21
           :thumb_url "fsdjflkjfd.gif"
           :gif_url "fafa.gif"}
          {:type "gif"
           :title "Title"
           :id 21
           :gif_url "fafa.gif"
           :thumb_url "fsdjflkjfd.gif"}])))
