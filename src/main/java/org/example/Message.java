package example;

import java.util.Map;

public class Message {
  private  Map<String, String> content;
  private EnrichmentType enrichmentType;

  public Message(Map<String, String> content, EnrichmentType type) {
    this.content = content;
    this.enrichmentType = type;
  }

  public enum EnrichmentType {
    MSISDN;
  }

  public String get(String key) {
    return this.content.get(key);
  }

  public void set(String key, String value) {
    this.content.put(key, value);
  }

  public EnrichmentType getType() {
    return this.enrichmentType;
  }

  public Map<String, String> getContent() {
    return this.content;
  }

  public String toString() {
    for (String i : this.content.keySet()) {
      System.out.println(i + " " + this.content.get(i));
    }
    return null;
  }
}
