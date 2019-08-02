(ns gfycat-telegram-bot.core
  (:require [clojure.core.async :refer [<!!]]
            [clojure.string :as str]
            [environ.core :refer [env]]
            [morse.handlers :as h]
            [morse.polling :as p]
            [morse.api :as t]
            [gfycat-api.core :as gif-api]
            [clojure.set :refer [rename-keys]]
            [gfycat-api.util :refer [get-gfycats-with]])
  (:gen-class))

; TODO: fill correct token
(def token (env :telegram-token))
(def not-blank? (complement str/blank?))
; Gfycat API
(def client-id (env :client-id))
(def client-secret (env :client-secret))
(defn to-telegram-array [array]
  (map #(into {:type 'gif :gif_width 100 :gif_height 100} (rename-keys %1 {:gfyId :id :miniPosterUrl :thumb_url
                                                   :max1mbGif :gif_url}))   array ))
(h/defhandler handler

              (h/command-fn "start"
                            (fn [{{id :id :as chat} :chat}]
                              (println "Bot joined new chat: " chat)
                              (t/send-text token id "Welcome to gfycat_telegram_bot!")))

              (h/command-fn "help"
                            (fn [{{id :id :as chat} :chat}]
                              (println "Help was requested in " chat)
                              (t/send-text token id "Help is on the way")))

              (h/inline-fn (fn [{id :id query :query :as inline}]
                             (if (not-blank? query)
                               (let [gfycat-token (gif-api/get-token client-id client-secret)
                                     search-result (get-gfycats-with [:gfyId :miniPosterUrl :max1mbGif]
                                                                     (gif-api/search gfycat-token query))]

                                 (println inline)
                                 (println id)
                                 (println (to-telegram-array search-result))
                                 (if (seq search-result)
                                   (t/answer-inline token id {} (to-telegram-array search-result)
                                                                      ))))))
  ; (h/message-fn
  ;   (fn [{{id :id} :chat :as message}]
  ;     (println "Intercepted message: " message)
  ;     (t/send-text token id "I don't do a whole lot ... yet.")))
)

(defn -main
  [& args]
  (when (some str/blank? [token client-id client-secret])
    (println "Please provde token in TELEGRAM_TOKEN environment variable!")
    (System/exit 1))

  (println "Starting the gfycat_telegram_bot")
  (<!! (p/start token handler)))
