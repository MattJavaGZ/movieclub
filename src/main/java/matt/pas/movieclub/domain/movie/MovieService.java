package matt.pas.movieclub.domain.movie;

import matt.pas.movieclub.domain.genre.Genre;
import matt.pas.movieclub.domain.genre.GenreRepository;
import matt.pas.movieclub.domain.movie.dto.MovieDto;
import matt.pas.movieclub.domain.movie.dto.MovieSaveDto;
import matt.pas.movieclub.storage.FileStorageService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private MovieRepository movieRepository;
    private GenreRepository genreRepository;
    private FileStorageService fileStorageService;

    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository, FileStorageService fileStorageService) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.fileStorageService = fileStorageService;
    }

    public List<MovieDto> findAllPromotedMovies() {
        final List<Movie> allPromotedMovies = movieRepository.findAllByPromotedIsTrue();
        return allPromotedMovies.stream()
                .map(MovieDtoMapper::map)
                .toList();
    }

    public Optional<MovieDto> findById(Long id) {
        return movieRepository.findById(id)
                .map(MovieDtoMapper::map);
    }

    public List<MovieDto> findAllByCategoryName(String categoryName) {
        return movieRepository.findAllByGenre_NameIgnoreCase(categoryName).stream()
                .map(MovieDtoMapper::map)
                .toList();
    }

    public void addMovie(MovieSaveDto movieToSave) {
        Movie movie = new Movie();
        movie.setTitle(movieToSave.getTitle());
        movie.setOryginalTitle(movieToSave.getOriginalTitle());
        movie.setPromoted(movieToSave.isPromoted());
        movie.setReleaseYear(movieToSave.getReleaseYear());
        movie.setShortDescription(movieToSave.getShortDescription());
        movie.setDescription(movieToSave.getDescription());
        movie.setYoutubeTrailerId(movieToSave.getYoutubeTrailerId());
        Genre genre = genreRepository.findByNameIgnoreCase(movieToSave.getGenre()).orElseThrow();
        movie.setGenre(genre);
        if (movieToSave.getPoster() != null && !movieToSave.getPoster().isEmpty()) {
            String savedFileName = fileStorageService.saveImage(movieToSave.getPoster());
            movie.setPoster(savedFileName);
        }
        movieRepository.save(movie);
    }

    public List<MovieDto> findTop10(int size) {
        Pageable pages = Pageable.ofSize(size);
        return movieRepository.findTopByRating(pages).stream()
                .map(MovieDtoMapper::map)
                .toList();
    }
}
