package org.example.service;

import org.example.article.Article;
import org.example.article.ArticleId;
import org.example.comment.Comment;
import org.example.comment.CommentId;
import org.example.exeption.DuplicateCommentExeption;
import org.example.exeption.NoExistArticleExeption;
import org.example.exeption.NoExistCommentExeption;
import org.example.repository.ArticleRepository;
import org.example.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;

public class CommentService {
  private final CommentRepository commentRepository;
  private final ArticleRepository articleRepository;

  public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository) {
    this.commentRepository = commentRepository;
    this.articleRepository = articleRepository;
  }


  public CommentId create(String text, ArticleId articleId) throws DuplicateCommentExeption, NoExistArticleExeption {
    try {
      CommentId commentId = commentRepository.generateId();
      Comment newComment = new Comment(commentId, articleId, text);
      commentRepository.create(newComment);
      Article article = articleRepository.findById(articleId);
      List<Comment> comments = article.getComments();
      if (comments == null) comments = new ArrayList<>();
      comments.add(newComment);
      article = article.withComment(comments);
      articleRepository.update(article);
      return commentId;
    } catch (DuplicateCommentExeption | NoExistArticleExeption e) {
      throw e;
    }
  }

  public void delete(CommentId id) throws NoExistCommentExeption {
    try {
      commentRepository.delete(id);
    } catch (NoExistCommentExeption e) {
      throw e;
    }
  }
}
