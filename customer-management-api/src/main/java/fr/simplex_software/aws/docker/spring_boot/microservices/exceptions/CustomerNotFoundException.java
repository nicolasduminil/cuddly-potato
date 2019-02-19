package fr.simplex_software.aws.docker.spring_boot.microservices.exceptions;

public class CustomerNotFoundException extends RuntimeException
{
  public CustomerNotFoundException (String exception)
  {
    super(exception);
  }
}
