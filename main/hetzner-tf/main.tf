# Set the variable value in *.tfvars file
# or using -var="hcloud_token=..." CLI option
variable "api_token" {}

# Configure the Hetzner Cloud Provider
provider "hcloud" {
  token = "${var.api_token}"
}
