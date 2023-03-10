(ns ultralight.core)

(defn js-map [a f] (.map (js/Array.from a) f))
(defn js-filter [a f] (.filter (js/Array.from a) f))

(defn $ ([q el] (.querySelector (or el js/document) q)) ([q] ($ q nil)))
(defn $$ ([q el] (.querySelectorAll (or el js/document) q)) ([q] ($$ q nil)))
(defn text [elements txt] (js-map elements (fn [el] (aset el "textContent" txt) el)))
(defn attr [elements k v] (js-map elements (fn [el] (.setAttribute el k v) el)))
(defn del [elements] (js-map elements (fn [el] (.remove el) el)))
(defn evt [elements event callback] (js-map elements (fn [el] (.addEventListener el event callback))))

; needs work ("variable arguments$ is undeclared")
; (defn log [& _args] #(js/console.log.apply js/console js/arguments) (aget js/arguments 0))
