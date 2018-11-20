package hyperlinker;

public class NameEntry {
  private String name;
  private String link;

  public NameEntry(String name, String link) {
    this.name = name;
    this.link = link;
  }

  public String getName() { return name; }
  public String getLink() { return link; }
  public void setName(String name) { this.name = name; }
  public void setLink(String link) { this.link = link; }
}
