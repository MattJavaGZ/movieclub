package matt.pas.movieclub.domain.comment;

import matt.pas.movieclub.domain.comment.dto.CommentDto;

public class CommentMapper {

    static CommentDto map(Comment comment) {
        String nick = comment.getUser() == null ? "Usunięty" : comment.getUser().getNick();
        return new CommentDto(
                comment.getId(),
                comment.getContent(),
                comment.getDateAdded(),
                nick
        );
    }
}
