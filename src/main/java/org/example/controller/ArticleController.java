package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.article.ArticleId;
import org.example.request.ArticleCreateRequest;
import org.example.response.ErrorResponse;
import org.example.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Service;

public class ArticleController implements Controller {
  private static final Logger LOG = LoggerFactory.getLogger(ArticleController.class);

  private final Service service;
  private final ArticleService articleService;
  private final ObjectMapper objectMapper;

  public ArticleController(Service service, ArticleService articleService, ObjectMapper objectMapper) {
    this.service = service;
    this.articleService = articleService;
    this.objectMapper = objectMapper;
  }

  @Override
  public void initializeEndpoints() {
    find();
    create();
    update();
    delete();
  }

  private void find() {

  }

  private void create() {
    service.post("/api/articles",
            (Request request, Response response) -> {
              response.type("application/json");
              String body = request.body();
              ArticleCreateRequest articleRequest = objectMapper.readValue(body, ArticleCreateRequest.class);
              try {
                ArticleId id = articleService.create(articleRequest.title(), articleRequest.tags());
                response.status(201);
                return objectMapper.writeValueAsString(new ArticleResponse(id));
              } catch (Exception e) {
                LOG.warn("Cannot create article", e);
                response.status(400);
                return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
              }
            }

    );
  }

  private void update() {

  }

  private void delete() {

  }
}
