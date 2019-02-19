package fr.simplex_software.aws.docker.spring_boot.microservices.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import fr.simplex_software.aws.docker.spring_boot.microservices.data.*;
import fr.simplex_software.aws.docker.spring_boot.microservices.exceptions.*;
import fr.simplex_software.aws.docker.spring_boot.microservices.repository.*;
import org.apache.commons.lang3.builder.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.*;
import java.math.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CustomerManagementController
{
  private Logger logger = LoggerFactory.getLogger(CustomerManagementController.class);
  @Autowired
  private CustomerManagementRepository customerManagementRepository;

  @RequestMapping(method = RequestMethod.POST)
  public HttpEntity<Customer> createCustomer(@RequestBody Customer customer)
  {
    logger.debug(String.format("*** createCustomer(): %s", new ReflectionToStringBuilder(customer).toString()));
    customerManagementRepository.save(customer);
    customer.add(linkTo(methodOn(CustomerManagementController.class).createCustomer(customer)).withSelfRel());
    return new ResponseEntity<>(customer, HttpStatus.OK);
  }

  @RequestMapping(value = "{id}", method = RequestMethod.GET)
  public HttpEntity<Customer> getCustomer(@PathVariable("id") BigInteger id)
  {
    logger.debug(String.format("*** getCustomer(): ID %d", id));
    return new ResponseEntity<>(customerManagementRepository.findById(id).get(), HttpStatus.OK);
  }

  @RequestMapping(value = "{id}", method = RequestMethod.PUT)
  public HttpEntity<Customer> updateCustomer(@PathVariable(value = "id") BigInteger id, @RequestBody Customer customer)
  {
    logger.debug(String.format("*** updateCustomer(): ID %d", id));
    Optional<Customer> cust = customerManagementRepository.findById(id);
    if (!cust.isPresent())
      throw new CustomerNotFoundException("id: " + id);
    Customer c = cust.get();
    Customer newCustomer = new Customer(customer);
    newCustomer.setPk(c.getPk());
    customerManagementRepository.save(newCustomer);
    return new ResponseEntity<>(newCustomer, HttpStatus.OK);
  }

  @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
  public ResponseEntity deleteCustomer(@PathVariable(value = "id") BigInteger id)
  {
    logger.debug(String.format("*** deleteCustomer(): ID %d", id));
    Optional<Customer> cust = customerManagementRepository.findById(id);
    if (!cust.isPresent())
      throw new CustomerNotFoundException("id: " + id);
    customerManagementRepository.delete(cust.get());
    return ResponseEntity.ok().build();
  }

  @RequestMapping(value = "firstName/{firstName}", method = RequestMethod.GET)
  public HttpEntity<List<Customer>> getCustomersByFirstName(@PathVariable("firstName") String firstName)
  {
    logger.debug(String.format("*** getCustomerByFirstName(): %s", firstName));
    return new ResponseEntity<>(customerManagementRepository.findByFirstName(firstName), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Iterable<Customer>> getCustomers()
  {
    logger.debug ("*** getCustomers(): Calling the findAll() method on the customer repository");
    return new ResponseEntity<>(customerManagementRepository.findAll(), HttpStatus.OK);
  }
}
