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

(ns ^{:doc "Functions to get terraform fill output.json into a given template."
      :author "Michael Jerger"}
  dda.template-js
  (:require
    [dda.template :as tpl]
    [cognitect.transit :as trs]
    [lumo.io :as io]))

(defn ^{:doc "Gets the template either from disk or classpath and fills in valuse from terraform output. The result is written to generate-to-path."}
  generate-with-terraform-output
  [template-path
   tf-output
   generate-to-path]
  (let [r (trs/reader :json)]
    (io/spit
      generate-to-path
      (tpl/render
        (io/slurp template-path)
        (trs/read r
          (io/slurp tf-output))))))
