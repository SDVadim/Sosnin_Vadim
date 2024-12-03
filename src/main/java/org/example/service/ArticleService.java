package org.example.service;

import org.example.Article.Article;
import org.example.Article.ArticleId;
import org.example.Repository.ArticleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ArticleService {
  private final ArticleRepository articleRepository;

  public ArticleService(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  public List<Article> findAll() {
    return articleRepository.findAll();
  }

  public Article findById(ArticleId id) throws Exception {
    try {
      return articleRepository.findById(id);
    } catch (Exception e) {
      throw new Exception(); //********************************
    }
  }

  public Article create(String title, Set<String> tags) throws Exception {
    try {
      ArticleId id = articleRepository.generateId();
      Article newArticle = new Article(id, title, tags, new ArrayList<>());
      articleRepository.create(newArticle);
      return newArticle;
    } catch (Exception e) {
      throw new Exception(); //******************************************
    }
  }

  public Article update(ArticleId id, String title, Set<String> tags) throws Exception {
    try {
      Article existsArticle = articleRepository.findById(id);
      Article newArticle = existsArticle.withName(title).withTags(tags);
      articleRepository.update(newArticle);
      return newArticle;
    } catch (Exception e) {
        throw new Exception(); //*************************************************************
    }
  }

  public void delete(ArticleId id) throws Exception {
    try {
      Article article = articleRepository.findById(id);
      /// Удаляю комментарии
      articleRepository.delete(id);
    } catch (Exception e) {
      throw new Exception(); //*****************************
    }
  }
}
