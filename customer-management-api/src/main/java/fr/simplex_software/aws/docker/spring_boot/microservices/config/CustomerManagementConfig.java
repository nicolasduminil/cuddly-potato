package fr.simplex_software.aws.docker.spring_boot.microservices.config;

import org.springframework.web.servlet.config.annotation.*;

public class CustomerManagementConfig implements WebMvcConfigurer
{
  @Override
  public void addCorsMappings(CorsRegistry registry)
  {
    registry.addMapping("/**").allowedMethods("PUT", "GET", "DELETE", "OPTIONS", "PATCH", "POST");
  }
}
