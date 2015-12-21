(ns cuid.core
  "Collision-resistant ids optimized for horizontal scaling and performance.
  Ported from https://github.com/ericelliott/cuid.
  (cuid) returns a short random string with some collision-busting measures.
  Safe to use as HTML element ID's, and unique server-side record lookups."
  (:require [clojure.string :as string]))

(defn ^:private to-string [x y]
  #?(:clj (Long/toString x y)
     :cljs (.toString x y)))

(defn ^:private timestamp []
  #?(:clj (System/currentTimeMillis)
     :cljs (.getTime (js/Date.))))

(def ^:private fingerprint
  #?(:clj {:pid (-> (java.lang.management.ManagementFactory/getRuntimeMXBean)
                    (.getName)
                    (string/split #"@")
                    (first))
           :hostname (-> (java.net.InetAddress/getLocalHost)
                         (.getCanonicalHostName))}
     :cljs {:mime-type js/navigator.mimeTypes.length
            :user-agent js/navigator.userAgent.length
            :global-count (-> js/window
                              (.-globalObject)
                              (js->clj)
                              (count))}))

(defonce ^:private counter (atom 0))
(def ^:const prefix "c")
(def ^:const block-size 4)
(def ^:const base 36)
(def ^:const discrete-values 1679616)

(defn ^:private pad [s n]
  (let [padded (str "000000000" s)
        len (count padded)]
    (subs padded (- len n) len)))

(defn ^:private random-block []
  (-> discrete-values
      (rand-int)
      (to-string base)
      (pad block-size)))

(defn ^:private safe-counter! []
  (-> counter
      (swap! inc)
      (str)))

(defn ^String cuid
  "A short random string with some collision-busting measure.
  Safe to use as HTML element ID's, and unique server-side record lookups."
  ([] (cuid fingerprint))
  ([coll] (str prefix
               (to-string (timestamp) base)
               (pad (safe-counter!) block-size)
               (pad (hash coll) block-size)
               (random-block)
               (random-block))))
