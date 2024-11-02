package example;

public class User {
  private String firstName;
  private String secondName;

  public User(String firstName, String secondName) {
    if (firstName == null || secondName == null) throw new IllegalArgumentException("User field can not be null");
    this.firstName = firstName;
    this.secondName = secondName;
  }

  public String getSecondName() {
    return secondName;
  }

  public String getFirstName() {
    return firstName;
  }
}
