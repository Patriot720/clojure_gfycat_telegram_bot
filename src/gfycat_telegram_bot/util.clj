(ns gfycat-telegram-bot.util
  )

(defn to-telegram-gif-array [array];
  (map (fn [{id :gfyId
              thumb_url :gif100px
              gif_url :max2mbGif
              title :title
              {{gif_width :width gif_height :height} :max2mbGif} :content-urls}]
         {:id id
:type "gif"
          :thumb_url thumb_url
          :gif_url gif_url
          :title title
          :gif_width gif_width
          :gif_height gif_height}) array))
