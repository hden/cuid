# cuid ![Build Status](https://circleci.com/gh/hden/cuid.svg?&style=shield&circle-token=67b78d410994ee5e3274458e1ddf7d1032cb7d5b) [![codecov](https://codecov.io/gh/hden/cuid/branch/master/graph/badge.svg)](https://codecov.io/gh/hden/cuid)
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Fhden%2Fcuid.svg?type=shield)](https://app.fossa.io/projects/git%2Bgithub.com%2Fhden%2Fcuid?ref=badge_shield)
Collision-resistant ids for clojure and clojurescript.
Ported from https://github.com/ericelliott/cuid.
(cuid) returns a short random string with some collision-busting measures.
Safe to use as HTML element ID's, and unique server-side record lookups.

![clojars](https://clojars.org/cuid/latest-version.svg)

## Usage

```clj
(ns example
  (:require [cuid.core :as c]))

(println (c/cuid))

;; optionally with custom fingerprint
(println (c/cuid {:foo "bar"}))
```

## License
MIT


[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Fhden%2Fcuid.svg?type=large)](https://app.fossa.io/projects/git%2Bgithub.com%2Fhden%2Fcuid?ref=badge_large)