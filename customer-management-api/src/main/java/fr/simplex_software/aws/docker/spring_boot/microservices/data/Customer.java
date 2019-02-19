package fr.simplex_software.aws.docker.spring_boot.microservices.data;

import org.springframework.hateoas.*;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.math.*;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "CUSTOMERS")
public class Customer extends ResourceSupport
{
  private static final long serialVersionUID = 1L;
  private BigInteger pk;
  private String firstName;
  private String lastName;
  private String street;
  private String city;
  private String state;
  private String zip;
  private String country;

  public Customer()
  {
  }

  public Customer(String firstName, String lastName, String street, String city, String state, String zip, String country)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.street = street;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.country = country;
  }

  public Customer(Customer customer)
  {
    this(customer.firstName, customer.lastName, customer.street, customer.city, customer.state, customer.zip, customer.country);
  }


  @Id
  @SequenceGenerator(name = "CUSTOMERS_ID_GENERATOR", sequenceName = "CUSTOMERS_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMERS_ID_GENERATOR")
  @Column(name = "CUSTOMER_ID", unique = true, nullable = false, length = 8)
  public BigInteger getPk()
  {
    return pk;
  }

  public void setPk(BigInteger pk)
  {
    this.pk = pk;
  }

  @XmlElement
  @Column(name = "FIRST_NAME", nullable = false, length = 40)
  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  @XmlElement
  @Column(name = "LAST_NAME", nullable = false, length = 40)
  public String getLastName()
  {
    return lastName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  @XmlElement
  @Column(name = "ADDRESS_STREET", nullable = false, length = 80)
  public String getStreet()
  {
    return street;
  }

  public void setStreet(String street)
  {
    this.street = street;
  }

  @XmlElement
  @Column(name = "ADDRESS_CITY", nullable = false, length = 80)
  public String getCity()
  {
    return city;
  }

  public void setCity(String city)
  {
    this.city = city;
  }

  @XmlElement
  @Column(name = "ADDRESS_STATE", nullable = false, length = 40)
  public String getState()
  {
    return state;
  }

  public void setState(String state)
  {
    this.state = state;
  }

  @XmlElement
  @Column(name = "ADDRESS_ZIP", nullable = false, length = 8)
  public String getZip()
  {
    return zip;
  }

  public void setZip(String zip)
  {
    this.zip = zip;
  }

  @XmlElement
  @Column(name = "ADDRESS_COUNTRY", nullable = false, length = 40)
  public String getCountry()
  {
    return country;
  }

  public void setCountry(String country)
  {
    this.country = country;
  }
}
