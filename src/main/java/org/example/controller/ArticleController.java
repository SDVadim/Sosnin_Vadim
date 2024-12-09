package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.article.Article;
import org.example.article.ArticleId;
import org.example.exeption.DuplicateArticleExeption;
import org.example.exeption.NoExistArticleExeption;
import org.example.exeption.NoExistCommentExeption;
import org.example.request.ArticleCreateRequest;
import org.example.request.ArticleUpdateRequest;
import org.example.response.*;
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
    service.get("/api/articles/:articleId",
        (Request request, Response response) -> {
          response.type("application/json");
          String body = request.body();
          ArticleId articleId = new ArticleId(Long.parseLong(request.params("articleId")));
          try {
            Article article = articleService.findById(articleId);
            response.status(201);
            return objectMapper.writeValueAsString(new ArticleFindResponse(article.getTitle(), article.getTags(), article.getComments()));
          } catch (NoExistArticleExeption e) {
            response.status(400);
            LOG.warn("Cannot find Article with articleId: {}", articleId);
            return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
          }
        }
      );
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
            return objectMapper.writeValueAsString(new ArticleCreateResponse(id));
          } catch (DuplicateArticleExeption e) {
            LOG.warn("Cannot create article", e);
            response.status(400);
            return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
          }
        }
    );
  }

  private void update() {
    service.post("/api/articles/:articleId",
        (Request request, Response response) -> {
          response.type("application/json");
          String body = request.body();
          ArticleId articleId = new ArticleId(Long.parseLong(request.params("articleId")));
          ArticleUpdateRequest articleUpdateRequest = objectMapper.readValue(body, ArticleUpdateRequest.class);
          try {
            articleService.update(articleId, articleUpdateRequest.title(), articleUpdateRequest.tags());
            response.status(201);
            return objectMapper.writeValueAsString(new ArticleUpdateResponse(articleId));
          } catch (NoExistArticleExeption e) {
            LOG.warn("Cannot find Article with articleId: {} to update", articleId);
            response.status(400);
            return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
          }
        }
    );
  }

  private void delete() {
    service.delete(
        "/api/articles/:articleId",
        (Request request, Response response) -> {
          response.type("application/json");
          ArticleId articleId = new ArticleId(Long.parseLong(request.params("articleId")));
          try {
            articleService.delete(articleId);
            response.status(201);
            return objectMapper.writeValueAsString(new ArticleDeleteResponse(articleId));
          } catch (Exception  e) {
            LOG.warn("Cannot find Article with articleId: {} to delete", articleId);
            response.status(400);
            return objectMapper.writeValueAsString(new ArticleDeleteResponse(articleId));
          }
        }
    );
  }
}
