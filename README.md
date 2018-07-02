# dda-cloudspec
[![Clojars Project](https://img.shields.io/clojars/v/dda/dda-cloudspec.svg)](https://clojars.org/dda/dda-cloudspec)

[![Slack](https://img.shields.io/badge/chat-clojurians-green.svg?style=flat)](https://clojurians.slack.com/messages/#dda-pallet/) | [<img src="https://domaindrivenarchitecture.org/img/meetup.svg" width=50 alt="DevOps Hacking with Clojure Meetup"> DevOps Hacking with Clojure](https://www.meetup.com/de-DE/preview/dda-pallet-DevOps-Hacking-with-Clojure) | [Website & Blog](https://domaindrivenarchitecture.org)

## Features
dda-cloudpec can test whole infrastructures, clouds and networks. It provides a close integration with terraform to instantiate probes and dda-serverspec a remote testing framework. This enables you to test remotely from freshly spawned probes eg.
* services listening to ip & port
* validity of certificate files
* validity of certificates by https
* network connectivity to remote systems

<a href="https://asciinema.org/a/185686?autoplay=1"><img src="https://asciinema.org/a/185686.png" width="836"/></a>

## Blog
Find a detailed setup howto here: https://domaindrivenarchitecture.org/posts/2018-06-24-dda-cloudspec/

## Setup in short
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
mach test               # tf apply & serverspec
mach terraform-destroy  # destroy server after test
```

## License
Published under [apache2.0 license](LICENSE.md)
