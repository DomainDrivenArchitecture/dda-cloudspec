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
        (recur
          (cs/replace
           result
           (re-pattern (cs/escape
                         replace
                         {\{ "\\{"
                          \} "\\}"}))
           value)
          (rest parse-results))))))
