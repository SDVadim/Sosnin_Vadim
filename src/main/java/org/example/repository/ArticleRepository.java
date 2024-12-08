package org.example.repository;

import org.example.article.Article;
import org.example.article.ArticleId;
import org.example.exeption.DuplicateArticleExeption;
import org.example.exeption.NoExistArticleExeption;
import org.example.exeption.NoExistCommentExeption;

import java.util.List;

public interface ArticleRepository {

  ArticleId generateId();

  List<Article> findAll();

  Article findById(ArticleId id) throws NoExistArticleExeption;

  void create(Article article) throws DuplicateArticleExeption;

  void update(Article article) throws NoExistArticleExeption;

  void delete(ArticleId id) throws NoExistArticleExeption;
}
