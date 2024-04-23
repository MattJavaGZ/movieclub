package matt.pas.movieclub.domain.comment;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findAllByMovie_Id(long id);
    List<Comment> findAllByUser_Email(String email);
}
