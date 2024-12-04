package org.example.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public record ArticleCreateRequest(String title, Set<String> tags) {
  @JsonCreator
  public ArticleCreateRequest(
          @JsonProperty("title") String title,
          @JsonProperty("tags") Set<String> tags
  ) {
    this.title = title;
    this.tags = tags;
  }
}
