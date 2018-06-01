output "instance_public_ip" {
  value = "${aws_instance.instance.public_ip}"
}
