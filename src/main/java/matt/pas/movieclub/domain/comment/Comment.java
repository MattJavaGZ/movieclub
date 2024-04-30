package matt.pas.movieclub.domain.comment;

import jakarta.persistence.*;
import matt.pas.movieclub.domain.movie.Movie;
import matt.pas.movieclub.domain.user.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne()
    @JoinColumn(name = "movie_id")
    private Movie movie;
    private String content;
    private LocalDateTime dateAdded;

    public Comment(User user, Movie movie, String content, LocalDateTime dateAdded) {
        this.user = user;
        this.movie = movie;
        this.content = content;
        this.dateAdded = dateAdded;
    }

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
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
}
