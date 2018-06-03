(defproject dda/dda-cloudspec "0.1.0-SNAPSHOT"
  :description "Tools for operating dda-cloudspec"
  :url "https://domaindrivenarchitecture.org"
  :license {:name "Apache License, Version 2.0"
            :url "https://www.apache.org/licenses/LICENSE-2.0.html"}
  :dependencies [[com.cognitect/transit-cljs "0.8.256"]]
  :source-paths ["main/cljs" "main/cljc"]
  :resource-paths ["main/resources"]
  :repositories [["snapshots" :clojars]
                 ["releases" :clojars]]
  :deploy-repositories [["snapshots" :clojars]
                        ["releases" :clojars]]
  :aliases {"test" ["doo" "node" "test"]}
  :cljsbuild
  {:builds {:minify {:source-paths ["main/cljc"]
                     :compiler {:optimizations :advanced
                                :pretty-print false}}
            :dev {:source-paths ["main/cljc"]
                  :compiler {:optimizations :whitespace}}
            :test {:id "test"
                   :source-paths ["main/cljc" "test/cljs"]
                   :compiler {:output-to "target/cljs-tests.js"
                              :output-dir "target"
                              :main dda.cloudspec-test-runner
                              :optimizations :none
                              :target :nodejs}}}}
  :profiles {:dev {:source-paths ["test/cljs"]
                   :plugins [[lein-cljsbuild "1.1.7"]
                             [lein-doo "0.1.10"]]}})
