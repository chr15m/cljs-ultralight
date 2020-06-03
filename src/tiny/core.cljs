(ns tiny.core)

(defn js-map [a f] (.map (js/Array.from a) f))
(defn js-filter [a f] (.filter (js/Array.from a) f))

(defn $ [q] (.querySelectorAll js/document q))
(defn text [elements txt] (js-map elements (fn [el] (aset el "textContent" txt) el)))
(defn attr [elements k v] (js-map elements (fn [el] (.setAttribute el k v) el)))
(defn del [elements] (js-map elements (fn [el] (js/console.log "el" el) (.remove el) el)))
(defn evt [elements event callback] (js-map elements (fn [el] (.addEventListener el event callback))))
