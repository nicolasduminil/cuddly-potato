package fr.simplex_software.aws.docker.spring_boot.microservices.repository;

import fr.simplex_software.aws.docker.spring_boot.microservices.data.*;
import org.springframework.data.repository.*;

import java.math.*;
import java.util.*;

public interface CustomerManagementRepository extends CrudRepository<Customer, BigInteger>
{
  public List<Customer> findByLastName (String lastName);
  public List<Customer> findByCountry (String country);
  public List<Customer> findByCity (String city);
  public List<Customer> findByZip (String zip);
  public List<Customer> findByState (String state);
  public List<Customer> findByStreet (String street);
  public List<Customer> findByFirstName(String firstName);
}
