package matt.pas.movieclub.web.admin;

import matt.pas.movieclub.domain.genre.GenreService;
import matt.pas.movieclub.domain.genre.dto.GenreDto;
import matt.pas.movieclub.domain.movie.MovieService;
import matt.pas.movieclub.domain.movie.dto.MovieSaveDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MovieManagementController {

    private GenreService genreService;
    private MovieService movieService;

    public MovieManagementController(GenreService genreService, MovieService movieService) {
        this.genreService = genreService;
        this.movieService = movieService;
    }

    @GetMapping("/admin/dodaj-film")
    String addMovieForm(Model model) {
        final List<GenreDto> genres = genreService.findAllGenres();
        model.addAttribute("genres", genres);
        final MovieSaveDto movie = new MovieSaveDto();
        model.addAttribute("movie", movie);
        return "admin/movie-form";
    }
    @PostMapping("/admin/dodaj-film")
        String addMovie(MovieSaveDto movie, RedirectAttributes redirectAttributes){
        movieService.addMovie(movie);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Film %s został zapisany".formatted(movie.getTitle())
        );
        return "redirect:/admin";
    }
}
