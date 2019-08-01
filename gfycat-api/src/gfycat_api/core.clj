(ns gfycat-api.core
  (:require [clojure.data.json :as json]
            [clj-http.client :as client]
            )
  (:gen-class))

(defn- clojure-stylify [key]
  (keyword (clojure.string/replace key #"_" "-")))
(def client-info (json/read-str (slurp ".client_info.json")
                                :key-fn clojure-stylify))

(defn- gfycat-request [path query-params]
  (-> (client/get (str "https://api.gfycat.com/v1/" path)
                  {:content-type :json
                   :accept "text/html"
                   :query-params query-params})
      :body
      (json/read-str :key-fn clojure-stylify)))

(defn get-token []
      (try
        (gfycat-request "oauth/token"
                                       {"grant_type" "client_credentials"
                                        "client_id" (:client-id client-info)
                                        "client_secret" (:client-secret client-info)})
        (catch Exception e (prn (ex-data e)  ))))

(defn search
  ([token query & [count cursor]]
   (gfycat-request "gfycats/search" {"search_text" query
                                     "count" count
                                     "cursor" cursor})))
