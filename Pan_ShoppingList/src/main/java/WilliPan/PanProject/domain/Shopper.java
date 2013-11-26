package WilliPan.PanProject.domain;

import java.util.Date;

import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "u_user")
public class Shopper extends Modelbase
{
  private static final long serialVersionUID = 1L;

  @Column(name = "u_username", length = 25, unique = true, nullable = false)
  private String username;
  @Column(name = "u_email", length = 45, unique = true, nullable = false)
  private String emailAddress;
  @Column(name = "u_firstname", length = 25, nullable = false)
  private String firstname;
  @Column(name = "u_lastname", length = 40, nullable = false)
  private String lastname;
  @Temporal(TemporalType.DATE)
  @Column(name = "u_birthday", nullable = false)
  private Date birthday;
  @Column(name = "u_password", length = 60, nullable = false)
  private String password;

  public Shopper()
  {
    birthday = new Date();
  }

  public Shopper(Long id, long version, String username, String emailAddress,
      String firstname, String lastname, DateTime birthday, String password)
  {
    super(id, false);
    this.username = username;
    this.emailAddress = emailAddress;
    this.firstname = firstname;
    this.lastname = lastname;
    this.birthday = birthday.toDate();
    this.password = password;
  }

  public void setUsername(String username)
  {
    if (username == null || username.isEmpty())
      throw new IllegalArgumentException("Empty username not allowed!");

    this.username = username;
  }

  public String getUsername()
  {
    return username;
  }

  public void setEmailAddress(String emailAddress)
  {
    if (emailAddress == null || emailAddress.isEmpty())
      throw new IllegalArgumentException("Empty emailAddress not allowed!");
    else if (emailAddress.split("\\@").length != 1)
      throw new IllegalArgumentException("No valid emailAddress handed over!");

    this.emailAddress = emailAddress;
  }

  public String getEmailAddress()
  {
    return emailAddress;
  }

  public String getFirstname()
  {
    return firstname;
  }

  public String getLastname()
  {
    return lastname;
  }

  public void setBirthday(DateTime birthday)
  {
    if (birthday == null)
      throw new IllegalArgumentException(
          "birthday with state null not allowed!");

    this.birthday = birthday.toDate();
  }
  
  public void setBirthday(Date birthday)
  {
    if (birthday == null)
      throw new IllegalArgumentException(
          "birthday with state null not allowed!");

    this.birthday = birthday;
  }

  public Date getBirthday()
  {
    return birthday;
  }

  public void setPassword(String password)
  {
    if (password == null || password.isEmpty())
      throw new IllegalArgumentException("Empty password not allowed!");

    this.password = password;
  }

  public String getPassword()
  {
    return password;
  }
}
