package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.controller.ArticleController;
import org.example.controller.ArticleFreemarkerController;
import org.example.controller.CommentController;
import org.example.controller.Controller;
import org.example.repository.ArticleRepository;
import org.example.repository.CommentRepository;
import org.example.repository.InMemoryArticleRepository;
import org.example.repository.InMemoryCommentRepository;
import org.example.service.ArticleService;
import org.example.service.CommentService;
import spark.Service;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    ObjectMapper objectMapper = new ObjectMapper();
    Service service = Service.ignite();

    ArticleRepository articleRepository = new InMemoryArticleRepository();
    CommentRepository commentRepository = new InMemoryCommentRepository();

    ArticleService articleService = new ArticleService(articleRepository, commentRepository);
    CommentService commentService = new CommentService(commentRepository, articleRepository);

    Controller articleController = new ArticleController(service, articleService, objectMapper);
    Controller commentController = new CommentController(service, commentService, objectMapper);
    Controller articleFreemarkerController = new ArticleFreemarkerController(service, articleService, TemplateFactory.freeMarkerEngine());

    Application appliccation = new Application(List.of(articleController, commentController, articleFreemarkerController));
    appliccation.start();
  }
}