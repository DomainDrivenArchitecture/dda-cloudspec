#!/usr/bin/env python
# -*- coding: utf-8 -*-

import os
import json
import pprint #pretty printing
from python_terraform import *

api_token = os.path.expandvars('$API_TOKEN')
t = Terraform(variables={'api_token': api_token})

return_code, stdout, stderr = t.plan(out='proposed_apply.plan')
# appy creates instances on hetzner
#return_code1, stdout1, stderr1 = t.apply('proposed_apply.plan', input=False, auto_approve=IsFlagged, )
output_result = t.output(json=IsFlagged)

pprint.pprint(output_result) #is a dictionary!
print('We can take a keyword(instance_public_ip, value) out of the dictionary: ' + str(output_result['instance_public_ip']['value'])) #is a dictionary!
print('Lets parse to Json...')
pprint.pprint(json.dumps(output_result))
