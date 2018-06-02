(ns ^{:doc "Functions related to the include of markdown-paged - handling the
list & heading indents of includes. This namespaces is implementation detail for
smeagol.include and not inteded for direct usage."
      :author "Michael Jerger"}
  dda.cloudspec.template
  (:require
    [clojure.string :as cs]))


(defn
  parse-stencil
  [template]
  (distinct
    (re-seq #".*(\{\{\s*(\S+)\s*\}\}).*" template)))

(defn
  render
  [template
   value-map]
  (loop [result template
         parse-results (parse-stencil template)]
    (if (empty? parse-results)
      result
      (let [parse (first parse-results)
            replace (nth parse 1)
            value (get value-map (nth parse 2))]
        (println value-map)
        (println value)
        (recur
          (cs/replace
           result
           (re-pattern (cs/escape
                         replace
                         {\{ "\\{"
                          \} "\\}"}))
           value)
          (rest parse-results))))))
