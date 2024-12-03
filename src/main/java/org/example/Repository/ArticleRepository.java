package org.example.Repository;

import org.example.Article.Article;
import org.example.Article.ArticleId;

import java.util.List;

public interface ArticleRepository {

  ArticleId generateId();

  List<Article> findAll();

  Article findById(ArticleId id) throws Exception;

  Article create(Article article) throws Exception;

  Article update(Article article) throws Exception;

  void delete(ArticleId id) throws Exception;
}
