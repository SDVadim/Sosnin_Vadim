package org.example.repository;

import org.example.comment.Comment;
import org.example.comment.CommentId;
import org.example.exeption.DuplicateCommentExeption;
import org.example.exeption.NoExistArticleExeption;
import org.example.exeption.NoExistCommentExeption;

import java.util.List;

public interface CommentRepository {

  CommentId generateId();

  List<Comment> findAll();

  Comment findById(CommentId id) throws NoExistCommentExeption;

  void create(Comment comment) throws DuplicateCommentExeption;

  void update(Comment coment) throws NoExistCommentExeption;

  void delete(CommentId commentId) throws NoExistCommentExeption;
}
