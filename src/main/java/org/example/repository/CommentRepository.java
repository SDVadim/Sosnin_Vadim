package org.example.repository;

import org.example.comment.Comment;
import org.example.comment.CommentId;

import java.util.List;

public interface CommentRepository {

  CommentId generateId();

  List<Comment> findAll();

  Comment findById(CommentId id) throws Exception;

  void create(Comment comment) throws Exception;

  void update(Comment coment);

  void delete(CommentId commentId);
}
