package org.example.service;

import org.example.article.Article;
import org.example.article.ArticleId;
import org.example.comment.Comment;
import org.example.exeption.DuplicateArticleExeption;
import org.example.exeption.NoExistArticleExeption;
import org.example.exeption.NoExistCommentExeption;
import org.example.repository.ArticleRepository;
import org.example.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ArticleService {
  private final ArticleRepository articleRepository;
  private final CommentRepository commentRepository;

  public ArticleService(ArticleRepository articleRepository, CommentRepository commentRepository) {
    this.articleRepository = articleRepository;
    this.commentRepository = commentRepository;
  }

  public List<Article> findAll() {
    return articleRepository.findAll();
  }

  public Article findById(ArticleId id) throws NoExistArticleExeption {
    try {
      return articleRepository.findById(id);
    } catch (NoExistArticleExeption e) {
      throw e;
    }
  }

  public ArticleId create(String title, Set<String> tags) throws DuplicateArticleExeption {
    try {
      ArticleId id = articleRepository.generateId();
      Article newArticle = new Article(id, title, tags, new ArrayList<>());
      articleRepository.create(newArticle);
      return id;
    } catch (DuplicateArticleExeption e) {
      throw e;
    }
  }

  public Article update(ArticleId id, String title, Set<String> tags) throws NoExistArticleExeption {
    try {
      Article existsArticle = articleRepository.findById(id);
      Article newArticle = existsArticle.withName(title).withTags(tags);
      articleRepository.update(newArticle);
      return newArticle;
    } catch (NoExistArticleExeption e) {
        throw e;
    }
  }

  public void delete(ArticleId id) throws NoExistArticleExeption, NoExistCommentExeption {
    try {
      Article article = articleRepository.findById(id);
      for (Comment comment : article.getComments()) {
        commentRepository.delete(comment.getId());
      }
      articleRepository.delete(id);
    } catch (NoExistArticleExeption | NoExistCommentExeption e) {
      throw e;
    }
  }
}
