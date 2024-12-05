package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.comment.CommentId;
import org.example.request.CommentCreateRequest;
import org.example.response.CommentCreateResponse;
import org.example.response.CommentDeleteResponse;
import org.example.response.ErrorResponse;
import org.example.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.Request;
import spark.Response;
import spark.Service;

public class CommentController implements Controller{
  private final Logger LOG = LoggerFactory.getLogger(CommentController.class);

  private final Service service;
  private final CommentService commentService;
  private final ObjectMapper objectMapper;

  public CommentController(Service service, CommentService commentService, ObjectMapper objectMapper) {
    this.service = service;
    this.commentService = commentService;
    this.objectMapper = objectMapper;
  }

  @Override
  public void initializeEndpoints() {
    create();
    delete();
  }

  public void create() {
    service.post("/api/comments",
        (Request request, Response response) -> {
          response.type("application/json");
          String body = request.body();
          CommentCreateRequest commentCreateRequest = objectMapper.readValue(body, CommentCreateRequest.class);
          try {
            CommentId commentId = commentService.create(commentCreateRequest.text(), commentCreateRequest.articleId());
            response.status(201);
            return objectMapper.writeValueAsString(new CommentCreateResponse(commentId));
          } catch (Exception e) {
            LOG.warn("Cannot create comment", e);
            response.status(400);
            return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
          }
        }
    );
  }

  public void delete() {
    service.delete("/api/comments/:commentId",
        (Request request, Response response) -> {
          response.type("application/json");
          String body = request.body();
          CommentId commentId = new CommentId(Long.parseLong(request.params("commentId")));
          try {
            commentService.delete(commentId);
            response.status(201);
            return objectMapper.writeValueAsString(new CommentDeleteResponse(commentId));
          } catch (Exception e) {
            LOG.warn("Cannot find comment with commentId: {} to delete", commentId);
            response.status(400);
            return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
          }
        }
    );
  }
}
