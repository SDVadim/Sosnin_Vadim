package example;

public class EnrichByMsisdn implements Enrichment {
  private UserData users;

  public EnrichByMsisdn(UserData users) {
    this.users = users;
  }

  @Override
  public Message enrich(Message message) {
    if (message == null) return message;

    String msisdn = message.get("msisdn");
    User user = users.findByMsisdn(msisdn);
    if (user != null) {
      message.set("firstName", user.getFirstName());
      message.set("secondName", user.getSecondName());
    }
    return message;
  }
}
