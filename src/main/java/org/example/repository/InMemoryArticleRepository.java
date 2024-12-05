package org.example.repository;

import org.example.article.Article;
import org.example.article.ArticleId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryArticleRepository implements ArticleRepository {
  private final AtomicLong nextId = new AtomicLong(0);
  private final Map<Long, Article> repo = new ConcurrentHashMap<>();

  @Override
  public ArticleId generateId() {
    return new ArticleId(nextId.incrementAndGet());
  }

  @Override
  public List<Article> findAll() {
    return new ArrayList<>(repo.values());
  }

  @Override
  public Article findById(ArticleId id) throws Exception {
    Article article = repo.get(id);
    if (article == null) {
      throw new Exception(""); //****************************
    } else {
      return article;
    }
  }

  @Override
  public void create(Article article) throws Exception {
    if (repo.get(article.getId()) != null) {
      throw new Exception(""); //******************************
    } else {
      repo.put(article.getId(), article);
    }
  }

  @Override
  public void update(Article article) throws Exception {
    if (repo.get(article.getId()) == null) {
      throw new Exception(); //******************************
    } else {
      repo.put(article.getId(), article);
    }
  }

  @Override
  public void delete(ArticleId id) throws Exception {
    if (repo.get(id.getId()) == null) {
      throw new Exception(); //***************************
    } else {
      repo.remove(id.getId());
    }
  }
}
