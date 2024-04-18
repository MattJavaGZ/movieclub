package matt.pas.movieclub.web;

import matt.pas.movieclub.domain.movie.MovieService;
import matt.pas.movieclub.domain.movie.dto.MovieDto;
import matt.pas.movieclub.domain.rating.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class MovieController {
    private MovieService movieService;
    private RatingService ratingService;

    public MovieController(MovieService movieService, RatingService ratingService) {
        this.movieService = movieService;
        this.ratingService = ratingService;
    }

    @GetMapping("/film/{id}")
    String movie (Model model, @PathVariable Long id, Authentication authentication){
         MovieDto movie = movieService.findById(id)
                         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("movie", movie);

        if (authentication != null) {
            final String userEmail = authentication.getName();
            final Integer curentRating = ratingService.getRating(userEmail, id).orElse(0);
            model.addAttribute("userRating", curentRating);
        }
        return "movie";
    }
    @GetMapping("/top10")
        String topMovies(Model model){
        final List<MovieDto> top10 = movieService.findTop10(10);
        model.addAttribute("movies", top10);
        model.addAttribute("heading", "Top 10");
        model.addAttribute("description", "10 najlepiej ocenianych filmów");
        return "movie-listing";
    }
}
