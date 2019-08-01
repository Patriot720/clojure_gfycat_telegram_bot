(ns gfycat-api.util)

(defn get-gfycats-with [keys search-result]
  "returns list of gfycats with selected parameters,
   removes all other parameters"
  (map #(select-keys % keys) (:gfycats search-result)))
