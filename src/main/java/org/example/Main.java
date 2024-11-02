package example;

import java.util.HashMap;
import java.util.Map;

public class Main {
  public static void main(String[] args) {
    User user1 = new User("Vadim", "Sosnin");
    UserData users = new UserData();
    users.updateUserByMsisdn("+79125912363", user1);

    Map<String, String> input= new HashMap<>();
    input.put("action", "button_click");
    input.put("page", "book_card");
    input.put("msisdn", "+79125912363");
    Message message = new Message(input, Message.EnrichmentType.MSISDN);

    EnrichmentService service = new EnrichmentService();
    service.addEnrichment(null, new EnrichByMsisdn(users));
    message = service.enrich(message);

    for (String e : message.getContent().keySet()) {
      System.out.println(e + ": " + message.getContent().get(e));
    }
  }
}
