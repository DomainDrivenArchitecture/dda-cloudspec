resource "aws_instance" "instance" {
  ami           = "${data.aws_ami.ubuntu_16.id}"
  instance_type = "t2.micro"
  key_name      = "${aws_key_pair.developer_ssh_key.key_name}"
}
