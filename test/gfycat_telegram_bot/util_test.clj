(ns gfycat-telegram-bot.util-test
  (:require [clojure.test :refer [deftest testing is]]
            [gfycat-telegram-bot.util :as util]
            [gfycat-telegram-bot.fixtures :as fixtures]))

(deftest to-telegram-gif-array-test
  (is (= (util/to-telegram-gif-array (:gfycats fixtures/single-gfycat-search-result))
         '({:gif_url "https://thumbs.gfycat.com/FondFocusedClownanemonefish-max-1mb.gif"
          :gif_width 280
        :type "gif"
          :thumb_url "https://thumbs.gfycat.com/FondFocusedClownanemonefish-max-1mb.gif"
          :gif_height 158
          :id "fondfocusedclownanemonefish"
          :title "Canadian Flag waving animated using MIR plug in after effects - free motion graphics"
}))))
