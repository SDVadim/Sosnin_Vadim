package org.example;

import example.*;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class succesfulTest {

   @Test
  void CheckReturnedMessageTest() {
     User user1 = new User("Vadim", "Sosnin");
     UserData users = new UserData();
     users.updateUserByMsisdn("+79125912363", user1);

     Map<String, String> input= new HashMap<>();
     input.put("action", "button_click");
     input.put("page", "book_card");
     input.put("msisdn", "+79125912363");
     Message message = new Message(input, EnrichmentType.MSISDN);

     EnrichmentService service = new EnrichmentService();
     service.addEnrichment(EnrichmentType.MSISDN, new EnrichByMsisdn(users));
     message = service.enrich(message);

     Message correctMessage = new Message(Map.of(
             "firstName", "Vadim",
             "action", "button_click",
             "page", "book_card",
             "msisdn", "+79125912363",
             "secondName", "Sosnin"
     ), EnrichmentType.MSISDN);

     assertEquals(message.getContent(), correctMessage.getContent());
     assertEquals(message.getType(), correctMessage.getType());
   }

   @Test
   void CheckReturnedMessageMsisdnTest() {
      User user1 = new User("Vadim", "Sosnin");
      UserData users = new UserData();
      users.updateUserByMsisdn("+79125912363", user1);

      Map<String, String> input= new HashMap<>();
      input.put("msisdn", "+79125912363");
      Message message = new Message(input, EnrichmentType.MSISDN);

      EnrichmentService service = new EnrichmentService();
      service.addEnrichment(EnrichmentType.MSISDN, new EnrichByMsisdn(users));
      message = service.enrich(message);

      Message correctMessage = new Message(Map.of(
              "firstName", "Vadim",
              "msisdn", "+79125912363",
              "secondName", "Sosnin"
      ), EnrichmentType.MSISDN);

      assertEquals(message.getContent(), correctMessage.getContent());
      assertEquals(message.getType(), correctMessage.getType());
   }
}
