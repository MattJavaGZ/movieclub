package matt.pas.movieclub.web.admin;

import matt.pas.movieclub.domain.genre.GenreService;
import matt.pas.movieclub.domain.genre.dto.GenreDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GenreManagementController {
    private GenreService genreService;

    public GenreManagementController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/admin/dodaj-gatunek")
        String addGenreForm (Model model) {
        final GenreDto genre = new GenreDto();
        model.addAttribute("genre", genre);
        return "admin/genre-form";
    }
    @PostMapping("/admin/dodaj-gatunek")
    String addGenre(GenreDto genre, RedirectAttributes redirectAttributes){
        genreService.addGenre(genre);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                String.format("Gatunek %s został dodany", genre.getName())
        );
        return "redirect:/admin";
    }
}
