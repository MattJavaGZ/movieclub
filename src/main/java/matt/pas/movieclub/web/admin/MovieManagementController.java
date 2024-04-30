package matt.pas.movieclub.web.admin;

import jakarta.validation.Valid;
import matt.pas.movieclub.domain.genre.GenreService;
import matt.pas.movieclub.domain.genre.dto.GenreDto;
import matt.pas.movieclub.domain.movie.MovieService;
import matt.pas.movieclub.domain.movie.dto.MovieSaveDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
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
        gendersList(model);
        final MovieSaveDto movie = new MovieSaveDto();
        model.addAttribute("movie", movie);
        return "admin/movie-form";
    }
    @PostMapping("/admin/dodaj-film")
        String addMovie(Model model, @Valid @ModelAttribute("movie") MovieSaveDto movie, BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            gendersList(model);
            return "admin/movie-form";
        } else {
            movieService.addMovie(movie);
            redirectAttributes.addFlashAttribute(
                    AdminController.NOTIFICATION_ATTRIBUTE,
                    "Film %s został zapisany".formatted(movie.getTitle())
            );
            return "redirect:/admin";
        }
    }
    @GetMapping("/admin/edytuj-film/{id}")
        String editMovieForm(Model model, @PathVariable long id){
        gendersList(model);
        final MovieSaveDto movieSaveDto = movieService.findByIdToSave(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("movie", movieSaveDto);
        model.addAttribute("id", id);
        return "admin/movie-edit-form";
    }
    @PostMapping("/admin/edytuj-film/{id}")
        String editMovie(Model model, @Valid @ModelAttribute("movie") MovieSaveDto movie, BindingResult bindingResult,
                         @PathVariable long id) {
        if (bindingResult.hasErrors()) {
            gendersList(model);
            return "admin/movie-edit-form";
        } else {
            movieService.editMovie(movie, id);
        }
        return "redirect:/film/" + id;
    }

    private void gendersList(Model model) {
        final List<GenreDto> genres = genreService.findAllGenres();
        model.addAttribute("genres", genres);
    }
}
