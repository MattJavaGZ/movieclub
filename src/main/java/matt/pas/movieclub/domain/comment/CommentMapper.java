package matt.pas.movieclub.domain.comment;

import matt.pas.movieclub.domain.comment.dto.CommentDto;

public class CommentMapper {

     static CommentDto map (Comment comment){
         return new CommentDto(
                 comment.getId(),
                 comment.getContent(),
                 comment.getDateAdded()
         );
     }
}
