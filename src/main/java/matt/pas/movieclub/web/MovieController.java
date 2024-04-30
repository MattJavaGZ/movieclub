package matt.pas.movieclub.web;

import matt.pas.movieclub.domain.comment.CommentService;
import matt.pas.movieclub.domain.comment.dto.CommentDto;
import matt.pas.movieclub.domain.movie.MovieService;
import matt.pas.movieclub.domain.movie.dto.MovieDto;
import matt.pas.movieclub.domain.rating.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class MovieController {
    private MovieService movieService;
    private RatingService ratingService;
    private CommentService commentService;

    public MovieController(MovieService movieService, RatingService ratingService, CommentService commentService) {
        this.movieService = movieService;
        this.ratingService = ratingService;
        this.commentService = commentService;
    }

    @GetMapping("/film/{id}")
    String movie(Model model, @PathVariable Long id, Authentication authentication) {
        MovieDto movie = movieService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("movie", movie);

        final List<CommentDto> allCommentsByMovie = commentService.findAllCommentsByMovieId(id);
        model.addAttribute("comments", allCommentsByMovie);

        if (authentication != null) {
            final String userEmail = authentication.getName();
            final Integer curentRating = ratingService.getRating(userEmail, id).orElse(0);
            model.addAttribute("userRating", curentRating);
        }
        return "movie";
    }

    @GetMapping("/top10")
    String topMovies(Model model) {
        final List<MovieDto> top10 = movieService.findTop10(10);
        model.addAttribute("movies", top10);
        model.addAttribute("heading", "Top 10");
        model.addAttribute("description", "10 najlepiej ocenianych filmów");
        return "movie-listing";
    }

    @GetMapping("/find-form")
    String findForm(){
        return "find-form";
    }
    @GetMapping("/find")
    String findByTitle(Model model, @RequestParam String title) {
        final List<MovieDto> moviesByTitle = movieService.findByTitle(title);
        model.addAttribute("movies", moviesByTitle);
        if (moviesByTitle.isEmpty()) {
            model.addAttribute("heading", "Żaden film nie został odnaleziony");
        }
        else model.addAttribute("heading", "Wyszukane filmy:");
        return "movie-listing";
    }
    @GetMapping("/admin/usun-film/{id}")
        String deleteMovie(@PathVariable long id) {
        movieService.deleteMovie(id);
        return "redirect:/";
    }
}
