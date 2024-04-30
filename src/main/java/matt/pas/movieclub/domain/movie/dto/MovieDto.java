package matt.pas.movieclub.domain.movie.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import matt.pas.movieclub.domain.genre.Genre;

import java.util.Objects;

public class MovieDto {

    private Long id;
    private String title;
    private String oryginalTitle;
    private int releaseYear;
    private String shortDescription;
    private String description;
    private String youtubeTrailerId;
    private String genre;
    private boolean promoted;
    private String poster;
    private double avgRating;
    private int ratingCount;

    public MovieDto(Long id, String title, String oryginalTitle, int releaseYear, String shortDescription,
                    String description, String youtubeTrailerId, String genre, boolean promoted, String poster,
                    double avgRating, int ratingCount) {
        this.id = id;
        this.title = title;
        this.oryginalTitle = oryginalTitle;
        this.releaseYear = releaseYear;
        this.shortDescription = shortDescription;
        this.description = description;
        this.youtubeTrailerId = youtubeTrailerId;
        this.genre = genre;
        this.promoted = promoted;
        this.poster = poster;
        this.avgRating = avgRating;
        this.ratingCount = ratingCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOryginalTitle() {
        return oryginalTitle;
    }

    public void setOryginalTitle(String oryginalTitle) {
        this.oryginalTitle = oryginalTitle;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYoutubeTrailerId() {
        return youtubeTrailerId;
    }

    public void setYoutubeTrailerId(String youtubeTrailerId) {
        this.youtubeTrailerId = youtubeTrailerId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isPromoted() {
        return promoted;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDto movieDto = (MovieDto) o;
        return releaseYear == movieDto.releaseYear && promoted == movieDto.promoted && Double.compare(movieDto.avgRating, avgRating) == 0 && ratingCount == movieDto.ratingCount && Objects.equals(id, movieDto.id) && Objects.equals(title, movieDto.title) && Objects.equals(oryginalTitle, movieDto.oryginalTitle) && Objects.equals(shortDescription, movieDto.shortDescription) && Objects.equals(description, movieDto.description) && Objects.equals(youtubeTrailerId, movieDto.youtubeTrailerId) && Objects.equals(genre, movieDto.genre) && Objects.equals(poster, movieDto.poster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, oryginalTitle, releaseYear, shortDescription, description, youtubeTrailerId, genre, promoted, poster, avgRating, ratingCount);
    }
}
