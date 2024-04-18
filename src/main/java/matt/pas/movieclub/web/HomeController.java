package matt.pas.movieclub.web;

import matt.pas.movieclub.domain.movie.MovieService;
import matt.pas.movieclub.domain.movie.dto.MovieDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private MovieService movieService;

    public HomeController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    String home(Model model){
        final List<MovieDto> allPromotedMovies = movieService.findAllPromotedMovies();
        model.addAttribute("heading", "Promowane filmy");
        model.addAttribute("description", "Filmy polecane przez nasz zespół");
        model.addAttribute("movies", allPromotedMovies);
        return "movie-listing";
    }
}
