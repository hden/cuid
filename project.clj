(defproject cuid "0.1.2"
  :description "Collision-resistant ids optimized for horizontal scaling and performance."
  :url "https://github.com/hden/cuid"
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :global-vars {*warn-on-reflection* true}
  :plugins [[lein-cloverage "1.0.13"]])
