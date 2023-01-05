(defproject pdfshot "0.1.4"
  :description "PDF screenshot generator for web pages"
  :url "https://github.com/xcoo/pdfshot"
  :license {:name "Apache License, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [org.clojure/core.async "1.5.648"]
                 [org.clojure/clojurescript "1.11.4"]]
  :plugins [[lein-cljsbuild "1.1.7"]]
  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src"]
                        :compiler {:main pdfshot.core
                                   :output-to "target/pdfshot.js"
                                   :target :nodejs
                                   :optimizations :simple
                                   :npm-deps {"puppeteer" "19.4.1"
                                              "express" "4.18.2"}
                                   :install-deps true}}]})
