package matt.pas.movieclub.domain.comment.dto;

import java.time.LocalDateTime;

public class CommentDto {
    private Long id;
    private String content;
    private LocalDateTime dateAdded;
    private String authorNick;

    public CommentDto(Long id, String content, LocalDateTime dateAdded, String authorNick) {
        this.id = id;
        this.content = content;
        this.dateAdded = dateAdded;
        this.authorNick = authorNick;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getAuthorNick() {
        return authorNick;
    }

    public void setAuthorNick(String authorNick) {
        this.authorNick = authorNick;
    }
}
