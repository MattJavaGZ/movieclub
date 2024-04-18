package matt.pas.movieclub.domain.genre;

import jakarta.transaction.Transactional;
import matt.pas.movieclub.domain.genre.dto.GenreDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GenreService {

    private GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Optional<GenreDto> findGenreByName(String genreName){
        return genreRepository.findByNameIgnoreCase(genreName)
                .map(GenreMapper::map);
    }

    public List<GenreDto> findAllGenres(){
        return StreamSupport.stream(genreRepository.findAll().spliterator(), false)
                .map(GenreMapper::map)
                .toList();
    }
    @Transactional
    public void addGenre (GenreDto genreDto) {
        final Genre genreToSave = new Genre();
        genreToSave.setName(genreDto.getName());
        genreToSave.setDescription(genreDto.getDescription());
        genreRepository.save(genreToSave);
    }
}
