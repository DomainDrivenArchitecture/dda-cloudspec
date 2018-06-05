resource "aws_key_pair" "developer_ssh_key" {
  key_name   = "cloudspec_ssh_key"
  public_key = "${file("~/.ssh/id_rsa.pub")}"
}
