package org.example.repository;

import org.example.article.Article;
import org.example.article.ArticleId;

import java.util.List;

public interface ArticleRepository {

  ArticleId generateId();

  List<Article> findAll();

  Article findById(ArticleId id) throws Exception;

  void create(Article article) throws Exception;

  void update(Article article) throws Exception;

  void delete(ArticleId id) throws Exception;
}
