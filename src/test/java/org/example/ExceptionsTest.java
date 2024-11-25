package org.example;

import example.*;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExceptionsTest {

  @Test
  public void CheckNullNameTest() {
    String name = null;
    String secondName = "Sosnin";
    Exception exception = assertThrows(IllegalArgumentException.class, () -> new User(name, secondName));
    assertEquals("User field can not be null", exception.getMessage());
    String name2 = "Vadim";
    String secondName2 = null;
    Exception exception2 = assertThrows(IllegalArgumentException.class, () -> new User(name2, secondName2));
    assertEquals("User field can not be null", exception2.getMessage());
  }

  @Test
  public void CheckNullEnrichmentType() {
    User user1 = new User("Vadim", "Sosnin");
    UserData users = new UserData();
    users.updateUserByMsisdn("+79125912363", user1);

    Map<String, String> input= new HashMap<>();
    input.put("action", "button_click");
    input.put("page", "book_card");
    input.put("msisdn", "+79125912363");
    Message message = new Message(input, EnrichmentType.MSISDN);
    EnrichmentService service = new EnrichmentService();
    Exception exception = assertThrows(IllegalArgumentException.class, () -> service.addEnrichment(null, new EnrichByMsisdn(users)));
    assertEquals("type can not be null", exception.getMessage());
  }

  @Test
  public void CheckNullEnrichment() {
    User user1 = new User("Vadim", "Sosnin");
    UserData users = new UserData();
    users.updateUserByMsisdn("+79125912363", user1);

    Map<String, String> input= new HashMap<>();
    input.put("action", "button_click");
    input.put("page", "book_card");
    input.put("msisdn", "+79125912363");
    Message message = new Message(input, EnrichmentType.MSISDN);
    EnrichmentService service = new EnrichmentService();
    Exception exception = assertThrows(IllegalArgumentException.class, () -> service.addEnrichment(EnrichmentType.MSISDN, null));
    assertEquals("enrichment can not be null", exception.getMessage());
  }
}
