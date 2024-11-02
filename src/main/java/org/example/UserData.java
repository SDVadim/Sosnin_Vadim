package example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserData implements UserRepository{
  private Map<String, User> users = new ConcurrentHashMap<>();

  public User findByMsisdn(String msisdn) {
    if (msisdn == null) throw new IllegalArgumentException("MSISDN can not be null");
    return users.get(msisdn);
  }

  public void updateUserByMsisdn(String msisdn, User user) {
    if (msisdn == null) throw new IllegalArgumentException("MSISDN can not be null");
    if (user == null) throw new IllegalArgumentException("Can not change null user");
    users.put(msisdn, user);
  }
}
