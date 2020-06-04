(ns tiny.demo
  (:require [tiny.core :as t]))

(js/console.log "hello")

(defn button-click [ev]
  (let [entered-text (-> (t/$ "input") (aget 0) .-value)
        updated-text (.concat "Updated text: " entered-text)]
    (-> (t/$ "p")
        (t/text updated-text))))

(-> (t/$ "#loading") (t/del))
(-> (t/$ "button") (t/attr "class" "primary"))
(-> (t/$ "button") (t/evt "click" button-click))

(defn pinger []
  (->
    (js/fetch "https://blockstream.info/api/mempool")
    (.then (fn [r] (.json r)))
    (.then (fn [m]
             (js/console.log m)
             (js/console.log (aget m "count") (mod (aget m "count") 2))
             (-> (t/$ "#mempool-count")
                 (t/text (if
                           (== (mod (aget m "count") 2) 1)
                           "odd"
                           "even")))
             (js/setTimeout pinger 10000)))))

(defn main! []
  (pinger))
