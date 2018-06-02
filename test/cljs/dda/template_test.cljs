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
more {{symbol2}} x {{symbol2}} should be distinct.")))))


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
              "symbol2" "bar"})))))
