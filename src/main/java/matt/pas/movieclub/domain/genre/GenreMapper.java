package matt.pas.movieclub.domain.genre;

import matt.pas.movieclub.domain.genre.dto.GenreDto;

public class GenreMapper {

    static GenreDto map(Genre genre){
        return new GenreDto(
                genre.getId(),
                genre.getName(),
                genre.getDescription()
        );
    }
}
