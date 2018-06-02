(ns cloudspec-test-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [pjstadig.humane-test-output]
            [dda.cloudspec.template-test]))

(doo-tests 'dda.cloudspec.template-test)
