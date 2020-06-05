(ns ultralight.demo
  (:require [ultralight.core :as u]))

(js/console.log "hello")

; an event handler that updates a p tag's text
(defn button-click [ev]
  (let [entered-text (-> (u/$ "input") (aget 0) .-value)
        updated-text (.concat "Updated text: " entered-text)]
    (-> (u/$ "p")
        (u/text updated-text))))

; delete the loading spinner div
(-> (u/$ "#loading") (u/del))
; set a class of "primary" on the button
(-> (u/$ "button") (u/attr "class" "primary"))
; add an event handler to the button
(-> (u/$ "button") (u/evt "click" button-click))

; a function which periodically fetches data
; and updates a span tag according to the result
(defn pinger []
  (->
    (js/fetch "https://blockstream.info/api/mempool")
    (.then (fn [r] (.json r)))
    (.then (fn [m]
             (-> (u/$ "#mempool-count")
                 (u/text (if
                           (== (mod (aget m "count") 2) 1)
                           "odd"
                           "even")))
             (js/setTimeout pinger 10000)))))

(defn main! []
  (pinger))
