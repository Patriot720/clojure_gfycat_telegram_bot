(ns gfycat-telegram-bot.core
  (:require [clojure.core.async :refer [<!!]]
            [clojure.string :as str]
            [environ.core :refer [env]]
            [morse.handlers :as h]
            [morse.polling :as p]
            [morse.api :as t]
            [gfycat-api.core :as gif-api]
            [gfycat-telegram-bot.util :refer [to-telegram-gif-array]])
  (:gen-class))

; TODO: fill correct token
(def token (env :telegram-token))
(def not-blank? (complement str/blank?))

; Gfycat API token
; (def client-id (env :client-id))
; (def client-secret (env :client-secret))
; (def gfycat-token (gif-api/get-token client-id client-secret))


(defn respond-with-gifs [{id :id query :query cursor :offset :as inline}]
  (if (not-blank? query)
    (if-let [{gfycats :gfycats
              next_cursor :cursor}
             (gif-api/search query 50 cursor)]
      (t/answer-inline token id {
        :next_offset next_cursor} (to-telegram-gif-array gfycats)))))

(h/defhandler handler

              (h/command-fn "start"
                            (fn [{{id :id :as chat} :chat}]
                              (println "Bot joined new chat: " chat)
                              (t/send-text token id "Welcome to gfycat_telegram_bot!")))

              (h/command-fn "help"
                            (fn [{{id :id :as chat} :chat}]
                              (println "Help was requested in " chat)
                              (t/send-text token id "Help is on the way")))

              (h/inline-fn respond-with-gifs)

              (h/message-fn
               (fn [{{id :id} :chat :as message}]
                 (if message
                   (do (println "Intercepted message: " message)
                       (t/send-text token id "I don't do a whole lot ... yet."))))))

(defn -main
  [& args]
  (when (str/blank? token)
    (println "Please provide tokens in TELEGRAM_TOKEN, gfycats CLIENT_ID AND CLIENT_SECRET environment variables!")
    (System/exit 1))

  (println "Starting the gfycat_telegram_bot")
  (<!! (p/start token handler)))
