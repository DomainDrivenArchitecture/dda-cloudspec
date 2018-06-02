(ns dda.cloudspec-test-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [pjstadig.humane-test-output]
            [dda.template-test]))

(doo-tests 'dda.template-test)
