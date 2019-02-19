package fr.simplex_software.aws.docker.spring_boot.microservices;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

@SpringBootApplication
public class CustomerManagementApplication
{
 public static void main(String[] args) throws Exception
  {
    SpringApplication.run(CustomerManagementApplication.class, args);
  }
}
