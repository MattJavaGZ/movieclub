package matt.pas.movieclub.web;

import matt.pas.movieclub.domain.rating.RatingService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RatingController {

    private RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }
    @PostMapping("/ocen-film")
    public String addRating(@RequestParam long movieId, @RequestParam int rating, @RequestHeader String referer, Authentication authentication) {
        final String userEmail = authentication.getName();
        ratingService.addOrUpdateRating(userEmail, movieId, rating);
        return "redirect:" + referer;
    }
}
