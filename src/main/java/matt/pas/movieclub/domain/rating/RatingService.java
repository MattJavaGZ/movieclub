package matt.pas.movieclub.domain.rating;

import matt.pas.movieclub.domain.movie.Movie;
import matt.pas.movieclub.domain.movie.MovieRepository;
import matt.pas.movieclub.domain.user.User;
import matt.pas.movieclub.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService {

    private UserRepository userRepository;
    private MovieRepository movieRepository;
    private RatingRepository ratingRepository;

    public RatingService(UserRepository userRepository, MovieRepository movieRepository, RatingRepository ratingRepository) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.ratingRepository = ratingRepository;
    }

    public void addOrUpdateRating (String userEmail, long movieId, int rating){
        Rating ratingToUpdateOrSave = ratingRepository.findByUser_EmailAndMovie_Id(userEmail, movieId)
                .orElseGet(Rating::new);
        final User user = userRepository.findByEmailIgnoreCase(userEmail).orElseThrow();
        final Movie movie = movieRepository.findById(movieId).orElseThrow();
        ratingToUpdateOrSave.setUser(user);
        ratingToUpdateOrSave.setMovie(movie);
        ratingToUpdateOrSave.setRating(rating);
        ratingRepository.save(ratingToUpdateOrSave);
    }
    public Optional<Integer> getRating(String userEmail, long movieId){
        return ratingRepository.findByUser_EmailAndMovie_Id(userEmail, movieId)
                .map(Rating::getRating);
    }
}
