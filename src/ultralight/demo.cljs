(ns ultralight.demo
  (:require [ultralight.core :as u]))

; an event handler that updates a p tag's text
(defn button-click [_ev]
  (let [entered-text (-> (u/$$ "input") (aget 0) .-value)
        updated-text (.concat "Updated text: " entered-text)]
    (-> (u/$$ "p")
        (u/text updated-text))))

; delete the loading spinner div
(-> (u/$$ "#loading") (u/del))
; set a class of "primary" on the button
(-> (u/$$ "button") (u/attr "class" "primary"))
; add an event handler to the button
(-> (u/$$ "button") (u/evt "click" button-click))

; testing nested element load (li's of only list-2)
(let [ul (u/$ "#list-2")
      lis (u/$$ "li" ul)]
  (u/attr lis "style" "background-color: pink;"))

; a function which periodically fetches data
; and updates a span tag according to the result
(defn pinger []
  (->
    (js/fetch "https://hacker-news.firebaseio.com/v0/maxitem.json?print=pretty")
    (.then (fn [r] (.json r)))
    (.then (fn [m]
             (-> (u/$$ "#hn-max-item")
                 (u/text m))
             (js/setTimeout pinger 10000)))))

(defn main! []
  (pinger))
