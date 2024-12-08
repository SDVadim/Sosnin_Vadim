package org.example.exeption;

public class NoExistArticleExeption extends Exception {
  public NoExistArticleExeption(String message) {
    super(message);
  }
}
