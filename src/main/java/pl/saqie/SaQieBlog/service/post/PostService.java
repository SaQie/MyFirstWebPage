package pl.saqie.SaQieBlog.service.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.saqie.SaQieBlog.domain.Category;
import pl.saqie.SaQieBlog.domain.Post;
import pl.saqie.SaQieBlog.domain.User;
import pl.saqie.SaQieBlog.repository.PostRepository;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void saveNewPost(User userByUsername, Category categoryByName, Post post) {
        Post postToSave = new Post(post.getTopic(), post.getContent(), userByUsername, categoryByName);
        postRepository.save(postToSave);
    }
}
