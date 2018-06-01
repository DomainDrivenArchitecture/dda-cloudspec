resource "aws_instance" "instance" {
  ami           = "ami-82cf0aed"
  instance_type = "t2.micro"
}
