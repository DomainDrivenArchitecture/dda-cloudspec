;; Copyright 2014-2018 meissa. All Rights Reserved.
;;
;; Licensed under the Apache License, Version 2.0 (the "License");
;; you may not use this file except in compliance with the License.
;; You may obtain a copy of the License at
;;
;;      http://www.apache.org/licenses/LICENSE-2.0
;;
;; Unless required by applicable law or agreed to in writing, software
;; distributed under the License is distributed on an "AS-IS" BASIS,
;; WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
;; See the License for the specific language governing permissions and
;; limitations under the License.

(ns ^{:doc "Functions related to the include of markdown-paged - handling the
list & heading indents of includes. This namespaces is implementation detail for
smeagol.include and not inteded for direct usage."
      :author "Michael Jerger"}
  dda.template
  (:require
    [clojure.string :as cs]))


(defn
  parse-stencil
  [template]
  (distinct
    (into
      (re-seq #".*(\{\{\s*(\S+)\s*\}\}).*" template)
      (re-seq #".*(\{\{\s*(\[.+\])\s*\}\}).*" template))))

(defn
  render
  [template
   value-map]
  (loop [result template
         parse-results (parse-stencil template)]
    (if (empty? parse-results)
      result
      (let [parse (first parse-results)
            replace
              (re-pattern (cs/escape
                            (nth parse 1)
                            {\{ "\\{"
                             \} "\\}"
                             \[ "\\["
                             \] "\\]"}))
            raw-path (nth parse 2)
            path (if (cs/starts-with? raw-path "[")
                     (filter
                       (fn [element] (not (cs/blank? element)))
                       (cs/split raw-path #"[\[\]\s]"))
                     (vector raw-path))
            value (get-in value-map path)]
       (recur
         (cs/replace
          result
          replace
          value)
         (rest parse-results))))))
