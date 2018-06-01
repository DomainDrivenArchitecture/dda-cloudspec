provider "aws" {
  region     = "eu-central-1"
}

resource "aws_instance" "example" {
  ami           = "ami-82cf0aed"
  instance_type = "t2.micro"
}
