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
  dda.template-js
  (:require
    [dda.template :as tpl]
    [cognitect.transit :as trs]
    [lumo.io :as io]))

(defn
  generate-with-terraform-output
  [template-path
   tf-output-path
   generate-to-path]
  (let [r (trs/reader :json)]
    (println
      (trs/read r
        (io/slurp tf-output-path)))
    (io/spit
      generate-to-path
      (tpl/render
        (io/slurp template-path)
        (trs/read r
          (io/slurp tf-output-path))))))
