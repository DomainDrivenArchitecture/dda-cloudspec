# Set the variable value in *.tfvars file
# or using -var="hcloud_token=..." CLI option
variable "api_token" {}

# Configure the Hetzner Cloud Provider
provider "hcloud" {
  token = "${var.api_token}"
}

resource "hcloud_ssh_key" "developer_ssh_key" {
  name       = "cloudspec_ssh_key"
  public_key = "${file("~/.ssh/id_rsa.pub")}"
}

resource "hcloud_server" "instance" {
  name        = "cloudspec-test"
  image       = "ubuntu-16.04"
  server_type = "cx11"
  ssh_keys    = ["${hcloud_ssh_key.developer_ssh_key.id}"]
}

output "instance_public_ip" {
  value = "${hcloud_server.instance.ipv4_address}"
}
