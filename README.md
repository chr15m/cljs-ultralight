The problem: you have a static site and you want to add a sprinkle of interactivity, using Clojure, without bloating your build.

With `cljs-ultralight` we try to achieve this dream:

 * sub-`1k` build sizes.
 * Concise functions for common UI operations.
 * Clojure language & tooling (live-reload, editor integration, repl etc.)

`cljs-ultralight` is a group of functions to help you keep your cljs builds tiny when building simple UIs.
You can easily keep your bundle size under `1k` uncompressed with this.

Here is a [blog post about making ClojureScript UIs in 500 bytes](https://mccormick.cx/news/entries/clojurescript-uis-in-500-bytes).

# Usage

## Install Clojure dep

```
{:deps {io.github.chr15m/cljs-ultralight {:git/tag "LATEST-GIT-TAG" :git/sha "LATEST-GIT-SHA"}}}
```

## Install with `npm`

Alternatively you can use `npm` to install it into `node_modules`.

```
npm i cljs-ultralight
```

You'll have to manually add the sources in your `shadow-cljs.edn` if you use `npm` to install it.

```clojure
:source-paths [... "node_modules/cljs-ultralight/src"]
```

## Quick example

```clojure
(ns myapp.core
  (:require [ultralight.core :as u]))

(-> (u/$$ "#my-button")
    (u/evt "click"
           #(js/alert "Clicked!")))
```

# Documentation

The [source code is so small as to be self-documenting](https://github.com/chr15m/cljs-ultralight/blob/main/src/ultralight/core.cljs). 😅
When this changes I will add more documentation.

# Examples

Check out [the demo](./src/ultralight/demo.cljs) for more examples.

* `npm run build` to build it.
* `npm run size` to see the build size (will automatically build first).
* `npm run watch` to develop on it.

# Tips

To get tiny build sizes `cljs-ultralight` tries to use native JS constructs where ever possible.
You should do the same if you want those small build sizes.
Including a single equality operator for example will balloon a basic build size from `1k` to `94k`.
Here are some rules to follow to avoid this:

 * Use `#js {}`, `#js []`, etc. instead of Clojure data types.
 * Use `coercive-=` instead of `=`.
 * Use `(.concat string-1 string-2)` instead of `(str string-1 string-2)`.
 * Use `(.map #js [1 2 3] (fn [i] ...))` etc. instead of `(map (fn [i] ...) [1 2 3])`.
 * Use `#(... %)` instead of `partial`.
 * Use `js/console.log` instead of `print`.

The library [`applied-science/js-interop`](https://github.com/applied-science/js-interop/) works well too.
