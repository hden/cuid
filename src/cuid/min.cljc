(ns cuid.min
  "Collision-resistant ids optimized for horizontal scaling and performance.
  Ported from https://github.com/ericelliott/cuid.
  (cuid) returns a short random string with some collision-busting measures.
  Safe to use as HTML element ID's, and unique server-side record lookups.")

(defn ^:private timestamp []
  #?(:clj (System/currentTimeMillis)
     :cljs (.getTime (js/Date.))))

(defn ^:private to-string [x y]
  #?(:clj (Long/toString x y)
     :cljs (.toString x y)))

(defn ^:private pad [s n]
  (let [padded (str "000000000" s)
        len (count padded)]
    (subs padded (- len n) len)))

(defonce ^:private counter (atom 0))
(def ^:const prefix "c")
(def ^:const block-size 4)
(def ^:const base 36)
(def ^:const discrete-values 1679616)

(defn ^:private random-block []
  (-> discrete-values
      (rand-int)
      (to-string base)
      (pad block-size)))

(defn ^:private safe-counter! []
  (-> counter
      (swap! inc)
      (str)))

(defn ^String cuid [coll]
 "A short random string with some collision-busting measure.
 Safe to use as HTML element ID's, and unique server-side record lookups."
 (let [timestamp     (get coll :timestamp    timestamp)
       safe-counter! (get coll :counter      safe-counter!)
       fingerprint   (get coll :fingerprint  "")
       random-block  (get coll :random-block random-block)]
   (str prefix
        (to-string (timestamp) base)
        (pad (safe-counter!) block-size)
        (pad (fingerprint)   block-size)
        (pad (random-block)  block-size)
        (pad (random-block)  block-size))))
