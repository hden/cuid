# cuid ![Build Status](https://circleci.com/gh/hden/cuid.svg?&style=shield&circle-token=67b78d410994ee5e3274458e1ddf7d1032cb7d5b)
Collision-resistant ids for clojure and clojurescript.
Ported from https://github.com/ericelliott/cuid
(cuid) returns a short random string with some collision-busting measures.
Safe to use as HTML element ID's, and unique server-side record lookups.

![clojars](https://clojars.org/cuid/latest-version.svg)

## Usage

```clj
(ns example
  (:require [cuid.core :refer :all]))

(println (cuid))

;; optionally with custom fingerprint
(println (cuid {:foo "bar"}))
```

## License
MIT
