package matt.pas.movieclub.web;

import matt.pas.movieclub.domain.genre.GenreService;
import matt.pas.movieclub.domain.genre.dto.GenreDto;
import matt.pas.movieclub.domain.movie.MovieService;
import matt.pas.movieclub.domain.movie.dto.MovieDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
public class GenreController {
    private MovieService movieService;
    private GenreService genreService;

    public GenreController(MovieService movieService, GenreService genreService) {
        this.movieService = movieService;
        this.genreService = genreService;
    }

    @GetMapping("/gatunek/{genre}")
    String getGenre(Model model, @PathVariable String genre) {
        final List<MovieDto> moviesByCategoryName = movieService.findAllByCategoryName(genre);
        GenreDto genreByName = genreService.findGenreByName(genre).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("heading", genreByName.getName());
        model.addAttribute("description", genreByName.getDescription());
        model.addAttribute("movies", moviesByCategoryName);
        return "movie-listing";
    }
    @GetMapping("/gatunki-filmowe")
    String getGenresList(Model model){
        final List<GenreDto> genres = genreService.findAllGenres();
        model.addAttribute("genres", genres);
        return "genre-listing";
    }


}
