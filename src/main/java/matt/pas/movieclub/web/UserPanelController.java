package matt.pas.movieclub.web;

import matt.pas.movieclub.domain.movie.MovieRepository;
import matt.pas.movieclub.domain.movie.MovieService;
import matt.pas.movieclub.domain.movie.dto.MovieDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserPanelController {
    private MovieService movieService;

    public UserPanelController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/user")
        String userPanel(){
        return "user";
    }

    @GetMapping("/user/ocenione-filmy")
        String ratedMovies(Authentication authentication, Model model){
        final String userEmail = authentication.getName();
        final List<MovieDto> allMoviesRatedByUser = movieService.findAllByRatedByUser(userEmail);

        model.addAttribute("heading", "Ocenione filmy");
        if (allMoviesRatedByUser.isEmpty()) model.addAttribute("description",
                "Brak filmów ocenionych przez Ciebie");
        else model.addAttribute("description", "Filmy ocenione przez Ciebie");

        model.addAttribute("movies", allMoviesRatedByUser);
        return "movie-listing";
    }
    @GetMapping("/user/skomentowane-filmy")
        String commentedMovies (Authentication authentication, Model model){
        final String userEmail = authentication.getName();
        final List<MovieDto> allMovieCommentedByUser = movieService.findAllByCommentedByUser(userEmail);

        model.addAttribute("heading", "Skomentowane filmy");
        if (allMovieCommentedByUser.isEmpty()) model.addAttribute("description",
                "Brak filmów skomentowanych przez Ciebie");
        else model.addAttribute("description", "Filmy skomentowane przez Ciebie");

        model.addAttribute("movies", allMovieCommentedByUser);
        return "movie-listing";
    }
}
