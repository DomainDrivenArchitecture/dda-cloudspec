# dda-cloudspec
[![Clojars Project](https://img.shields.io/clojars/v/dda/dda-cloudspec.svg)](https://clojars.org/dda/dda-cloudspec)

[![Slack](https://img.shields.io/badge/chat-clojurians-green.svg?style=flat)](https://clojurians.slack.com/messages/#dda-pallet/) | [<img src="https://domaindrivenarchitecture.org/img/meetup.svg" width=50 alt="DevOps Hacking with Clojure Meetup"> DevOps Hacking with Clojure](https://www.meetup.com/de-DE/preview/dda-pallet-DevOps-Hacking-with-Clojure) | [Website & Blog](https://domaindrivenarchitecture.org)

## preparation
### install mach
```
sudo apt-get install npm
sudo npm install -g @juxt/mach

sudo bash -c "cd /usr/local/bin && curl -fsSLo boot https://github.com/boot-clj/boot-bin/releases/download/latest/boot.sh && chmod 755 boot"
```

### install awscli & terraform
```
apt install awscli

vi ~/.aws/credentials
[default]
aws_access_key_id = ACCESS_KEY
aws_secret_access_key = SECRET_KEY

curl -L -o /tmp/terraform_0.11.7_linux_amd64.zip https://releases.hashicorp.com/terraform/0.11.7/terraform_0.11.7_linux_amd64.zip
cd /tmp
unzip terraform_0.11.7_linux_amd64.zip
mv terraform /usr/local/bin/
```

### precondition
1. you should have ssh-client installed an a public ssh-key avail at ~/.ssh/id_rsa.pub
2. Your aws credentials should be configured

## usage
```
mach execute-serverspec # tf apply & serverspec
mach terraform-destroy  # destroy server after test
```
