package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.controller.ArticleController;
import org.example.controller.ArticleFreemarkerController;
import org.example.controller.CommentController;
import org.example.repository.ArticleRepository;
import org.example.repository.CommentRepository;
import org.example.repository.InMemoryArticleRepository;
import org.example.repository.InMemoryCommentRepository;
import org.example.service.ArticleService;
import org.example.service.CommentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class EndToEndTest {
  private Service service;

  @BeforeEach
  void beforeEach() {
    Service.ignite();
  }

  @AfterEach
  void afterEach() {
    service.stop();
    service.awaitStop();
  }

  @Test
  public void applicationTest() throws IOException, InterruptedException {
    ObjectMapper objectMapper = new ObjectMapper();

    ArticleRepository articleRepository = new InMemoryArticleRepository();
    CommentRepository commentRepository = new InMemoryCommentRepository();

    ArticleService articleService = new ArticleService(articleRepository, commentRepository);
    CommentService commentService = new CommentService(commentRepository, articleRepository);

    Application application = new Application(List.of(
        new CommentController(service, commentService, objectMapper),
        new ArticleController(service, articleService, objectMapper),
        new ArticleFreemarkerController(service, articleService, TemplateFactory.freeMarkerEngine())
    ));
    application.start();
    service.awaitInitialization();

    HttpResponse<String> response = HttpClient.newHttpClient().send(
        HttpRequest.newBuilder()
            .POST(
                BodyPublishers.ofString(
                    """
                            {
                              "title": "JAVA",
                              "tags": ["MTC"]
                            }
                          """
                )
            )
            .uri(URI.create("http://localhost:4567/api/articles"))
            .build(),
        HttpResponse.BodyHandlers.ofString(UTF_8)
    );
    assertEquals(201, response.statusCode());
  }

}
