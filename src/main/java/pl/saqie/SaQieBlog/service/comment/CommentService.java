package pl.saqie.SaQieBlog.service.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.saqie.SaQieBlog.domain.Comments;
import pl.saqie.SaQieBlog.domain.Post;
import pl.saqie.SaQieBlog.domain.User;
import pl.saqie.SaQieBlog.repository.CommentRepository;
import pl.saqie.SaQieBlog.repository.PostRepository;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    public void saveNewComment(Comments comments, Long id, User byUsername){
        Post byId = postRepository.getById(id);
        Comments commentToSave = new Comments(comments.getText(), byId, byUsername);
        commentRepository.save(commentToSave);
    }
}
