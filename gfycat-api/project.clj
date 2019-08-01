(defproject gfycat-api "0.1.0"
            :description "Clojure wrapper for Gfycat API"
            :url "http://example.com/FIXME"
            :license {:name "EPL-2.0"
                      :url "https://www.eclipse.org/legal/epl-2.0/"}
            :dependencies [[org.clojure/clojure "1.10.0"]
                           [clj-http "2.3.0"]
                           [org.clojure/data.json "0.2.6"]
                           [proto-repl "0.3.1"]]
            :main ^:skip-aot gfycat-api.core
            :target-path "target/%s"
            :profiles {:uberjar {:aot :all}})
