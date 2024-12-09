package org.example.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.article.ArticleId;

public record CommentCreateRequest(ArticleId articleId, String text) {
  @JsonCreator
  public CommentCreateRequest(
    @JsonProperty("articleId") ArticleId articleId,
    @JsonProperty("text") String text
  ) {
    this.articleId = articleId;
    this.text = text;
  }
}
