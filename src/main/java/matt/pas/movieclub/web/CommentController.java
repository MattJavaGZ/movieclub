package matt.pas.movieclub.web;

import matt.pas.movieclub.domain.comment.CommentService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("/dodaj-komentarz")
    public String addComment (@RequestParam String content, @RequestParam long movieId, @RequestHeader String referer,
                              Authentication authentication){
        final String userEmail = authentication.getName();
        commentService.addComment(userEmail, movieId, content);
        return "redirect:" + referer;
    }
    @GetMapping("/usun-komentarz/{id}")
    public String deleteComment (@PathVariable long id, @RequestHeader String referer){
        commentService.deleteById(id);
        return "redirect:" + referer;
    }


}
