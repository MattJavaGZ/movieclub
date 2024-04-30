package matt.pas.movieclub.web;

import matt.pas.movieclub.domain.favorite.Favorite;
import matt.pas.movieclub.domain.movie.MovieService;
import matt.pas.movieclub.domain.movie.dto.MovieDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Controller
public class FavoriteController {
    private Favorite favorite;
    private MovieService movieService;

    public FavoriteController(Favorite favorite, MovieService movieService) {
        this.favorite = favorite;
        this.movieService = movieService;
    }

    @GetMapping("/favorite")
        String favorite(Model model) {
        final Set<MovieDto> favoriteMovies = favorite.getFavoriteMovies();
        model.addAttribute("movies", favoriteMovies);
        if (favoriteMovies.isEmpty()) model.addAttribute("heading", "Brak filmów w ulubionych");
        else model.addAttribute("heading", "Twoje ulubione filmy");
        return "movie-listing";
    }
    @GetMapping("/favorite/dodaj/{id}")
        String addFavorite(@PathVariable long id, @RequestHeader String referer){
        final MovieDto movieDto = movieService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        favorite.addFavorite(movieDto);
        return "redirect:" + referer;
    }
    @GetMapping("/favorite/usun/{id}")
        String deleteFavorite(@PathVariable long id, @RequestHeader String referer){
        final MovieDto movieDto = movieService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        favorite.deleteMovieWithFavorite(movieDto);
        return "redirect:" + referer;
     }
}

