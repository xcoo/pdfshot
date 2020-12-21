(defproject pdfshot "0.1.0"
  :description "PDF screenshot generator for web pages"
  :url "https://github.com/xcoo/pdfshot"
  :license {:name "Apache License, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/core.async "1.3.610"]
                 [org.clojure/clojurescript "1.10.773"]]
  :plugins [[lein-cljsbuild "1.1.7"]]
  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src"]
                        :compiler {:main pdfshot.core
                                   :output-to "target/pdfshot.js"
                                   :target :nodejs
                                   :optimizations :simple
                                   :npm-deps {"puppeteer" "5.5.0"
                                              "express" "4.16.4"}
                                   :install-deps true}}]})
