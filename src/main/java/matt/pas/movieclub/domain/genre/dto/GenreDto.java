package matt.pas.movieclub.domain.genre.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class GenreDto {
    private Long id;
    @NotEmpty(message = "Nazwa kategorii nie może być pusta")
    private String name;
    @Size(max = 500, message = "Opis może zawieraź maksymalnie 500 znaków")
    @NotEmpty(message = "Opis kategorii nie może być pusty")
    private String description;

    public GenreDto() {
    }

    public GenreDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
