(ns cuid.core
  "Collision-resistant ids optimized for horizontal scaling and performance.
  Ported from https://github.com/ericelliott/cuid.
  (cuid) returns a short random string with some collision-busting measures.
  Safe to use as HTML element ID's, and unique server-side record lookups."
  (:require [clojure.string :as string]
            [cuid.min :as min]))

(def ^:private fingerprint
  #?(:clj (hash {:pid (-> (java.lang.management.ManagementFactory/getRuntimeMXBean)
                          (.getName)
                          (string/split #"@")
                          (first))
                 :hostname (-> (java.net.InetAddress/getLocalHost)
                               (.getCanonicalHostName))})
     :cljs (hash {:mime-type js/navigator.mimeTypes.length
                  :user-agent js/navigator.userAgent.length
                  :global-count (-> js/window
                                    (.-globalObject)
                                    (js->clj)
                                    (count))})))

(defn ^String cuid
  "A short random string with some collision-busting measure.
  Safe to use as HTML element ID's, and unique server-side record lookups."
  ([] (min/cuid {:fingerprint (constantly fingerprint)}))
  ([coll] (min/cuid {:fingerprint (constantly (hash coll))})))
