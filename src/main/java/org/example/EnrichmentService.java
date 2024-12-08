package example;

import java.util.concurrent.ConcurrentHashMap;

public class EnrichmentService {
  private ConcurrentHashMap<EnrichmentType, Enrichment> enrichments = new ConcurrentHashMap();

  public EnrichmentService(){};
  public EnrichmentService(ConcurrentHashMap<EnrichmentType, Enrichment> enrichments) {
    this.enrichments = enrichments;
  }

  public void addEnrichment(EnrichmentType type, Enrichment enrichment) {
    if (type == null) throw new IllegalArgumentException("type can not be null");
    if (enrichment == null) throw new IllegalArgumentException("enrichment can not be null");
    enrichments.put(type, enrichment);
  }

  public Message enrich(Message message) {
    return enrich(message, message.getType());
  }

  public Message enrich(Message message, EnrichmentType type) {
    Enrichment enrichment = enrichments.get(type);

    Message newMessage = enrichment.enrich(message);
    return newMessage;
  }
}
