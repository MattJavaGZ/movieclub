package matt.pas.movieclub.web.admin;

import jakarta.validation.Valid;
import matt.pas.movieclub.domain.genre.GenreService;
import matt.pas.movieclub.domain.genre.dto.GenreDto;
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
    String addGenre(@Valid @ModelAttribute("genre") GenreDto genre, BindingResult bindingResult,
                    RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            return "admin/genre-form";
        } else {
            genreService.addGenre(genre);
            redirectAttributes.addFlashAttribute(
                    AdminController.NOTIFICATION_ATTRIBUTE,
                    String.format("Gatunek %s został dodany", genre.getName())
            );
        }
        return "redirect:/admin";
    }
    @GetMapping("/admin/edytuj-gatunek/{id}")
        String editGenreForm(Model model, @PathVariable long id) {
        final GenreDto genre = genreService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("genre", genre);
        model.addAttribute("id", id);
        return "admin/genre-edit-form";
    }
    @PostMapping("/admin/edytuj-gatunek/{id}")
        String editGenre(@Valid @ModelAttribute("genre") GenreDto genre, BindingResult bindingResult,
                         @PathVariable long id, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()){
            return "admin/genre-edit-form";
        } else {
            genreService.editGenre(id, genre);
            redirectAttributes.addFlashAttribute(
                    AdminController.NOTIFICATION_ATTRIBUTE,
                    String.format("Gatunek %s został zaktualizowany", genre.getName())
            );
        }
        return "redirect:/admin";
    }
    @GetMapping("/admin/usun-gatunek/{id}")
        String deleteGenre(@PathVariable long id){
        genreService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        genreService.deleteGenre(id);
        return "redirect:/gatunki-filmowe";
    }
}
