package matt.pas.movieclub.domain.movie;

import matt.pas.movieclub.domain.movie.dto.MovieDto;
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
                movie.getGenre().getName(),
                movie.isPromoted(),
                movie.getPoster(),
                avgRating,
                ratingCount
        );
    }
}
