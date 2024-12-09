package org.example.article;

import java.util.Objects;

public class ArticleId {
  private final long id;

  public ArticleId(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ArticleId articleId = (ArticleId) o;
    return id == articleId.getId();
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return String.valueOf(id);
  }

}
