(defproject gfycat_telegram_bot "0.1.1"
            :description "Telegram inline bot for  gfycat gifs"
            :url "https://github.com/Patriot720/clojure_gfycat_telegram_bot"

            :license {:name "Eclipse Public License"
                      :url "http://www.eclipse.org/legal/epl-v10.html"}

            :dependencies [[gfycat-api "0.1.3"]
                           [org.clojure/clojure "1.8.0"]
                           [environ             "1.1.0"]
                           [morse               "0.2.8"]]

            :main ^:skip-aot gfycat-telegram-bot.core
            :target-path "target/%s"

            :profiles {:uberjar {:aot :all}})
