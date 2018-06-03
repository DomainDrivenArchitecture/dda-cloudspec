# dda-cloudspec

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

### configure your ssh-key-on-aws
1. Add your key in main/terraform/key_pair.tf
2. Configure your key in main/terraform/instance.tf

## usage
```
mach execute-serverspec # tf apply & serverspec
mach terraform-destroy  # destroy server after test
```
