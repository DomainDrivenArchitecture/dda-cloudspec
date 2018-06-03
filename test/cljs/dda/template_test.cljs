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

(ns dda.template-test
  (:require [cljs.test :refer [deftest testing is]]
            [dda.template :as sut]))

(deftest test-parse-stencils
  (testing
    (is (= '(["Some {{symbol-to-replace}} surounding text"
                "{{symbol-to-replace}}" "symbol-to-replace"])
           (sut/parse-stencil "Some {{symbol-to-replace}} surounding text")))
    (is (= '(["Some {{ symbol-to-replace }} surounding text"
                "{{ symbol-to-replace }}" "symbol-to-replace"])
           (sut/parse-stencil "Some {{ symbol-to-replace }} surounding text")))
    (is (= '(["Some {{symbol-to-replace}} surounding text"
              "{{symbol-to-replace}}"
              "symbol-to-replace"]
             ["more {{symbol2}} x {{symbol2}} should be distinct."
              "{{symbol2}}"
              "symbol2"])
           (sut/parse-stencil "Some {{symbol-to-replace}} surounding text
more {{symbol2}} x {{symbol2}} should be distinct.")))
    (is (= '(["Some {{[path1 path2]}} surounding text"
              "{{[path1 path2]}}"
              "[path1 path2]"])
           (sut/parse-stencil "Some {{[path1 path2]}} surounding text")))))

(deftest test-render
  (testing
    (is (= "Some foo surounding text"
           (sut/render
             "Some {{symbol-to-replace}} surounding text"
             {"symbol-to-replace" "foo"})))
    (is (= "Some foo surounding text"
           (sut/render
             "Some {{ symbol-to-replace }} surounding text"
             {"symbol-to-replace" "foo"})))
    (is (= "Some foo surounding text
more bar x bar should be distinct."
           (sut/render
             "Some {{symbol-to-replace}} surounding text
more {{symbol2}} x {{symbol2}} should be distinct."
             {"symbol-to-replace" "foo"
              "symbol2" "bar"})))
    (is (= "Some foo surounding text"
           (sut/render
             "Some {{[path1 path2]}} surounding text"
             {"path1" {"path2" "foo"}})))))
