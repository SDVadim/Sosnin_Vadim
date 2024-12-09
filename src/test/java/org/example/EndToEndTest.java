package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.controller.ArticleController;
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

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.*;

class EndToEndTest {
  private Service service;

  @BeforeEach
  public void beforeEach() {
    service = Service.ignite();
  }

  @AfterEach
  public void afterEach() {
    service.stop();
    service.awaitStop();
  }

  @Test
  void applicationTest() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();

    ArticleRepository articleRepository = new InMemoryArticleRepository();
    CommentRepository commentRepository = new InMemoryCommentRepository();

    ArticleService articleService = new ArticleService(articleRepository, commentRepository);
    CommentService commentService = new CommentService(commentRepository, articleRepository);

    Application application = new Application(
        List.of(
            new CommentController(service, commentService, objectMapper),
            new ArticleController(service, articleService, objectMapper)
        )
    );
    application.start();
    service.awaitInitialization();

    HttpResponse<String> responseOfCreateArticle =
        HttpClient.newHttpClient()
            .send(
                HttpRequest.newBuilder()
                    .POST(
                        HttpRequest.BodyPublishers.ofString(
                            """
                                			{
                                			    "title": "Java",
                                			    "tags": ["MTC"]
                                			}
                                """
                        ))
                    .uri(URI.create("http://localhost:4567/api/articles"))
                    .build(),
                HttpResponse.BodyHandlers.ofString(UTF_8));
    assertEquals(201, responseOfCreateArticle.statusCode());

    HttpResponse<String> responseOfCreateComment =
        HttpClient.newHttpClient()
            .send(
                HttpRequest.newBuilder()
                    .POST(
                        HttpRequest.BodyPublishers.ofString(
                            """
                                    { 
                                        "articleId": 1,
                                        "text": "advanced java" 
                                    }
                                  """
                        ))
                    .uri(URI.create("http://localhost:4567/api/comments"))
                    .build(),
                HttpResponse.BodyHandlers.ofString(UTF_8));
    assertEquals(201, responseOfCreateComment.statusCode());

    HttpResponse<String> getArticleResponse = HttpClient.newHttpClient()
        .send(
            HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:4567/api/articles/1"))
                .build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
        );
    assertEquals(201, getArticleResponse.statusCode());

    HttpResponse<String> deleteArticleRequest = HttpClient.newHttpClient()
        .send(
            HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create("http://localhost:4567/api/articles/1"))
                .build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
        );
    assertEquals(201, deleteArticleRequest.statusCode());

    HttpResponse<String> getNoExistArticleResponse = HttpClient.newHttpClient()
        .send(
            HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create("http://localhost:4567/api/articles/1"))
                .build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
        );
    assertEquals(400, getNoExistArticleResponse.statusCode());

    HttpResponse<String> deleteNoExistArticleResponse = HttpClient.newHttpClient()
        .send(
            HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create("http://localhost:4567/api/articles/1"))
                .build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
        );
    assertEquals(400, deleteNoExistArticleResponse.statusCode());
  }
}