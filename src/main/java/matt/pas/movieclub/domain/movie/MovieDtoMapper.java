package matt.pas.movieclub.domain.movie;

import matt.pas.movieclub.domain.movie.dto.MovieDto;
import matt.pas.movieclub.domain.movie.dto.MovieSaveDto;
import matt.pas.movieclub.domain.rating.Rating;

public class MovieDtoMapper {

    static MovieDto map (Movie movie){
        final double avgRating = movie.getRatings().stream()
                .map(Rating::getRating)
                .mapToDouble(val -> val)
                .average().orElse(0);
        final int ratingCount = movie.getRatings().size();

        return new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getOryginalTitle(),
                movie.getReleaseYear(),
                movie.getShortDescription(),
                movie.getDescription(),
                movie.getYoutubeTrailerId(),
                movie.getGenre() != null ? movie.getGenre().getName() : "Brak",
                movie.isPromoted(),
                movie.getPoster(),
                avgRating,
                ratingCount
        );
    }
    static MovieSaveDto mapToSave (Movie movie){
        final MovieSaveDto movieSaveDto = new MovieSaveDto();
        movieSaveDto.setTitle(movie.getTitle());
        movieSaveDto.setOriginalTitle(movie.getOryginalTitle());
        movieSaveDto.setReleaseYear(movie.getReleaseYear());
        movieSaveDto.setShortDescription(movie.getShortDescription());
        movieSaveDto.setDescription(movie.getDescription());
        movieSaveDto.setYoutubeTrailerId(movie.getYoutubeTrailerId());
        movieSaveDto.setGenre(movie.getGenre().getName());
        movieSaveDto.setPromoted(movie.isPromoted());
        movieSaveDto.setPoster(null);
        return movieSaveDto;
    }
}
