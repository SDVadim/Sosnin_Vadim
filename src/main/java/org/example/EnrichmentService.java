package example;

import java.util.concurrent.ConcurrentHashMap;

public class EnrichmentService {
  private ConcurrentHashMap<Message.EnrichmentType, Enrichment> enrichments = new ConcurrentHashMap();

  public EnrichmentService(){};
  public EnrichmentService(ConcurrentHashMap<Message.EnrichmentType, Enrichment> enrichments) {
    this.enrichments = enrichments;
  }

  public void addEnrichment(Message.EnrichmentType type, Enrichment enrichment) {
    if (type == null) throw new IllegalArgumentException("type can not be null");
    if (enrichment == null) throw new IllegalArgumentException("enrichment can not be null");
    enrichments.put(type, enrichment);
  }

  public Message enrich(Message message) {
    return enrich(message, message.getType());
  }

  public Message enrich(Message message, Message.EnrichmentType type) {
    Enrichment enrichment = enrichments.get(type);

    Message newMessage = enrichment.enrich(message);
    return newMessage;
  }
}
