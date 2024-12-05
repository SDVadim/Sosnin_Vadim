package org.example.response;

import org.example.comment.Comment;

import java.util.List;
import java.util.Set;

public record ArticleFindResponse(String title, Set<String> tags, List<Comment> comments) {
}
