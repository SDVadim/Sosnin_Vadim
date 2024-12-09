package org.example.comment;

import org.example.article.ArticleId;

public class Comment {
  private final CommentId id;
  private final ArticleId articleId;
  private final String text;

  public Comment(CommentId id, ArticleId articleId, String text) {
    this.id = id;
    this.articleId = articleId;
    this.text = text;
  }

  public Comment withText(String newText) {
    return new Comment(this.id, this.articleId, newText);
  }

  public Comment withId(CommentId newId) {
    return new Comment(newId, this.articleId, this.text);
  }

  public CommentId getId() {
    return id;
  }

  public ArticleId getArticleId() {
    return articleId;
  }

  public String getText() {
    return text;
  }
}
