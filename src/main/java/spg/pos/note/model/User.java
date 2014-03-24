package spg.pos.note.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "users")
@Table(name = "users")
public class User extends Modelbase
{
  private static final long serialVersionUID = 1L;

  @Column(name = "name", length = 30, unique = true, nullable = false)
  private String username;
  @Column(name = "email", length = 45, unique = true, nullable = false)
  private String emailAddress;
  @Column(name = "firstname", length = 30, nullable = false)
  private String firstname;
  @Column(name = "lastname", length = 50, nullable = false)
  private String lastname;
  @Temporal(TemporalType.DATE)
  @Column(name = "birthday", nullable = false)
  private Date birthday;
  @Column(name = "password", length = 60, nullable = false)
  private String password; // Gonnna get hashed at some point far far away in time and space

  public User()
  {
    birthday = new Date();
  }

  public User(String username, String emailAddress,
      String firstname, String lastname, Date birthday, String password)
  {
    this.username = username;
    this.emailAddress = emailAddress;
    this.firstname = firstname;
    this.lastname = lastname;
    this.birthday = birthday;
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

    // Hash password here!

    this.password = password;
  }

  public String getPassword()
  {
    return password;
  }
}
