package matt.pas.movieclub.domain.movie;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    List<Movie> findAllByPromotedIsTrue();
    List<Movie> findAllByGenre_NameIgnoreCase (String genreName);
    @Query("select m from Movie m join m.ratings r group by m order by avg(r.rating) desc ")
    List<Movie> findTopByRating(Pageable pages);

    List<Movie> findAllByTitleContainsIgnoreCase(String title);

    List<Movie> findAllByRatings_User_Email(String email);
    List<Movie> findAllByComments_User_Email(String email);
}
