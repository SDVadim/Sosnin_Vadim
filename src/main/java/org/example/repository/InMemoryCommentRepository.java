package org.example.repository;

import org.example.comment.Comment;
import org.example.comment.CommentId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryCommentRepository implements CommentRepository {
  private final AtomicLong nextId = new AtomicLong(0);
  private final Map<CommentId, Comment> repo = new ConcurrentHashMap<>();

  @Override
  public CommentId generateId() {
    return new CommentId(nextId.incrementAndGet());
  }

  @Override
  public List<Comment> findAll() {
    return new ArrayList<>(repo.values());
  }

  @Override
  public Comment findById(CommentId id) throws Exception {
    Comment comment = repo.get(id);
    if (comment == null) {
      throw new Exception("");
    } else {
      return comment;
    }
  }

  @Override
  public void create(Comment comment) throws Exception {
    CommentId id = comment.getId();
    if (repo.get(id) == null) {
      repo.put(id, comment);
    } else {
      throw new Exception("");
    }
  }

  @Override
  public void update(Comment coment) throws Exception {
    CommentId id = coment.getId();
    if (repo.get(id) == null) {
      throw new Exception();
    } else {
      repo.put(id, coment);
    }
  }

  @Override
  public void delete(CommentId commentId) throws Exception {
    if (repo.get(commentId) == null) {
      throw new Exception();
    } else {
      repo.remove(commentId);
    }
  }
}
