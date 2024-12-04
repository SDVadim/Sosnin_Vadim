package org.example.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public record ArticleUpdateRequest(String title, Set<String> tags) {
  @JsonCreator
  public ArticleUpdateRequest(
          @JsonProperty("title") String title,
          @JsonProperty("tags") Set<String> tags
  ) {
    this.title = title;
    this.tags = tags;
  }
}
