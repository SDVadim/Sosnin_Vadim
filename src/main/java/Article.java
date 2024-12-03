import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Article {
  private final ArticleId id;
  private final String title;
  private final Set<String> tags;
  private final List<String> comments;

  public Article(ArticleId id, String title, Set<String> tags, List<String> comments) {
    this.id = id;
    this.title = title;
    this.tags = tags;
    this.comments = comments;
  }

  public long getId() {
    return id.getId();
  }

  public String getTitle() {
    return title;
  }

  public Set<String> getTags() {
    return tags;
  }

  public List<String> getComments() {
    return new ArrayList<>(comments);
  }
}
