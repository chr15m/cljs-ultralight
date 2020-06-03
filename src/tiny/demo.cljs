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

(defn main! [])
