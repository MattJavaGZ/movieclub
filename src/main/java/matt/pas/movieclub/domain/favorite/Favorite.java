package matt.pas.movieclub.domain.favorite;

import matt.pas.movieclub.domain.movie.dto.MovieDto;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Favorite {

    private Set<MovieDto> favoriteMovies = new HashSet<>();

    public int getFavoriveSize() {
        return favoriteMovies.size();
    }
    public void addFavorite(MovieDto movie){
        favoriteMovies.add(movie);
    }
    public void deleteMovieWithFavorite(MovieDto movieDto) {
        favoriteMovies.remove(movieDto);
    }
    public boolean isExist(MovieDto movieDto){
       return favoriteMovies.contains(movieDto);
    }
    public Set<MovieDto> getFavoriteMovies() {
        return favoriteMovies;
    }


    public void setFavoriteMovies(Set<MovieDto> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }


}
