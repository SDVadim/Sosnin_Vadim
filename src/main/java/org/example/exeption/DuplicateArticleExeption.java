package org.example.exeption;

public class DuplicateArticleExeption extends Exception {
  public DuplicateArticleExeption(String message) {
    super(message);
  }
}
