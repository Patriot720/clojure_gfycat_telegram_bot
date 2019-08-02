(defproject gfycat_telegram_bot "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [
[gfycat-api "0.1.3-SNAPSHOT"]
[org.clojure/clojure "1.8.0"]
                 [environ             "1.1.0"]
                 [morse               "0.2.8"]]

  :plugins [[lein-environ "1.1.0"]]

  :main ^:skip-aot gfycat-telegram-bot.core
  :target-path "target/%s"
  ; :jvm-opts ["-Dhttp.proxyHost=41.190.95.20" "-Dhttp.proxyPort=56167"]

  :profiles {:uberjar {:aot :all}})
