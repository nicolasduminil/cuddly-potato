package fr.simplex_software.aws.docker.spring_boot.microservices;

import fr.simplex_software.aws.docker.spring_boot.microservices.data.*;
import org.apache.commons.lang3.builder.*;
import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.slf4j.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.web.client.*;
import org.springframework.core.*;
import org.springframework.http.*;
import org.springframework.test.context.junit4.*;
import org.springframework.web.client.*;

import java.math.*;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerManagementApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerManagementControllerTest
{
  private Logger logger = LoggerFactory.getLogger(CustomerManagementControllerTest.class);
  private RestTemplate restTemplate = new RestTemplateBuilder().rootUri("http://localhost:8080/cm").build();
  private class CustomerList extends ArrayList<Customer> {};

  @Test
  public void testGetCustomers() throws Exception
  {
    logger.debug("*** testGetCustomers(): Calling CustomerManagementController.findAll()");
    ResponseEntity<Iterable<Customer>> resp = restTemplate.exchange("/api", HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Customer>>(){});
    assertEquals(resp.getStatusCode(), HttpStatus.OK);
    assertTrue(((Collection<?>)resp.getBody()).size() != 0);
  }

  @Test
  public void testGetCustomer() throws Exception
  {
    logger.debug("*** testGetCustomer(): Calling CustomerManagementController.findById()");
    ResponseEntity<Iterable<Customer>> resp = restTemplate.exchange("/api", HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Customer>>(){});
    assertEquals(resp.getStatusCode(), HttpStatus.OK);
    logger.debug("*** testGetCustomer(): {}", new ReflectionToStringBuilder(resp).toString());
    assertTrue(((Collection<?>)resp.getBody()).size() != 0);
    HttpEntity<Customer> customerHttpEntity = restTemplate.getForEntity("/api/" + resp.getBody().iterator().next().getPk(), Customer.class, BigInteger.class);
    assertEquals(((ResponseEntity<Customer>) customerHttpEntity).getStatusCode(), HttpStatus.OK);
    Customer customer = customerHttpEntity.getBody();
    assertNotNull(customer);
    assertEquals("Nicolas", customer.getFirstName());
  }

  @Test
  public void testGetCustomerByFirstName() throws Exception
  {
    logger.debug("*** testGetCustomerByFirstName(): Calling CustomerManagementController.findByFirstName()");
    HttpEntity<List<Customer>> customerHttpEntity = restTemplate.exchange("/api/firstName/Nicolas", HttpMethod.GET, null, new ParameterizedTypeReference<List<Customer>>(){});
    assertEquals(((ResponseEntity<List<Customer>>) customerHttpEntity).getStatusCode(), HttpStatus.OK);
    List<Customer> customers = customerHttpEntity.getBody();
    assertNotNull(customers);
    assertFalse(customers.isEmpty());
    assertEquals("Nicolas", customers.get(0).getFirstName());
  }

  @Test
  public void testCreateCustomer()
  {
    logger.debug("*** testCreateCustomer(): Calling CustomerManagementController.createCustomer()");
    Customer customer = new Customer ("firstName", "lastName", "street", "city", "state", "zip", "country");
    ResponseEntity<Customer> resp = restTemplate.postForEntity("/api", new HttpEntity<Customer> (customer), Customer.class);
    assertEquals(resp.getStatusCode(), HttpStatus.OK);
    customer = resp.getBody();
    assertEquals("firstName", customer.getFirstName());
    logger.debug("*** testCreateCustomer(): Customer with ID {} has been created. URL {}", customer.getPk(), customer.getLinks().get(0));
  }

  @Test
  public void testUpdateCustomer() throws Exception
  {
    logger.debug("*** testUpdateCustomer(): Calling CustomerManagementController.updateCustomer()");
    HttpEntity<List<Customer>> customerHttpEntity = restTemplate.exchange("/api/firstName/Nicolas", HttpMethod.GET, null, new ParameterizedTypeReference<List<Customer>>(){});
    assertEquals(((ResponseEntity<List<Customer>>) customerHttpEntity).getStatusCode(), HttpStatus.OK);
    List<Customer> customers = customerHttpEntity.getBody();
    assertNotNull(customers);
    assertFalse(customers.isEmpty());
    Customer customer = customers.get(0);
    assertEquals("Nicolas", customer.getFirstName());
    customer.setCountry("UK");
    HttpEntity<Customer> customerEntity = restTemplate.exchange("/api/" + customer.getPk(), HttpMethod.PUT, new HttpEntity<Customer> (customer), Customer.class);
    assertEquals (((ResponseEntity<Customer>) customerEntity).getStatusCode(), HttpStatus.OK);
    assertEquals (customerEntity.getBody().getCountry(), "UK");
  }

  @Test
  public void testDeleteCustomer() throws Exception
  {
    logger.debug("*** testDeleteCustomer(): Calling CustomerManagementController.deleteCustomer()");
    HttpEntity<List<Customer>> customerHttpEntity = restTemplate.exchange("/api/firstName/firstName", HttpMethod.GET, null, new ParameterizedTypeReference<List<Customer>>(){});
    ResponseEntity<Iterable<Customer>> resp = restTemplate.exchange("/api", HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Customer>>(){});
    assertEquals(((ResponseEntity<List<Customer>>) customerHttpEntity).getStatusCode(), HttpStatus.OK);
    List<Customer> customers = customerHttpEntity.getBody();
    assertFalse(customers.isEmpty());
    customers.forEach (c -> restTemplate.exchange ("/api/" + c.getPk(), HttpMethod.DELETE, null, Void.class));
  }
}
