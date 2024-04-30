package matt.pas.movieclub.domain.comment;

import matt.pas.movieclub.domain.comment.dto.CommentDto;
import matt.pas.movieclub.domain.movie.Movie;
import matt.pas.movieclub.domain.movie.MovieRepository;
import matt.pas.movieclub.domain.user.User;
import matt.pas.movieclub.domain.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private MovieRepository movieRepository;
    private UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, MovieRepository movieRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    public List<CommentDto> findAllCommentsByMovieId(long movieId){
        return commentRepository.findAllByMovie_Id(movieId).stream()
                .map(CommentMapper::map)
                .toList();
    }
    public void addComment(String userEmail, long movieId, String commentContent) {
        final Comment commentToSave = new Comment();
        final User user = userRepository.findByEmailIgnoreCase(userEmail).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        final Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        commentToSave.setUser(user);
        commentToSave.setMovie(movie);
        commentToSave.setContent(commentContent);
        commentToSave.setDateAdded(LocalDateTime.now());
        commentRepository.save(commentToSave);
    }
    public void deleteById(long id){
        commentRepository.deleteById(id);
    }
}
