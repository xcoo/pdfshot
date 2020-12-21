(ns pdfshot.message
  (:require [clojure.string :as string]))

(defn- red
  [s]
  (str "\033[31m" s "\033[0m"))

(defn- print-error-message
  [msg]
  (->> msg
       .text
       string/split-lines
       (map red)
       (interpose "\n")
       (apply str)
       js/console.error))

(defn- print-location
  [_ l]
  (js/console.error
   (red (str "        "
             (.-url l)
             (when-let [n (.-lineNumber l)]
               (str ":" (inc n)))))))

(defn print
  [msg]
  (case (.type msg)
    "error"
    (do
      (print-error-message msg)
      (when-let [st (.stackTrace msg)]
        (js/console.error (red "    StackTrace:"))
        (reduce print-location nil st)))
    (js/console.log (.text msg))))
