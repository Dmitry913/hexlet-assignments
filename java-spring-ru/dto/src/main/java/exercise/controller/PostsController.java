package exercise.controller;

import exercise.dto.CommentDTO;
import exercise.dto.PostDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.model.Comment;
import exercise.model.Post;
import exercise.repository.CommentRepository;
import exercise.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public List<PostDTO> allPosts() {
        return postRepository.findAll().stream()
                .map(post -> mapPost(post, commentRepository.findByPostId(post.getId()))).toList();
    }

    @GetMapping("/{id}")
    public PostDTO show(@PathVariable Long id) {
        return postRepository.findById(id).map(post -> mapPost(post, commentRepository.findByPostId(post.getId())))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Post with id %s not found", id)));
    }

    private PostDTO mapPost(Post postFrom, List<Comment> commentsFrom) {
        List<CommentDTO> mapComments =
                commentsFrom.stream().map(from -> new CommentDTO(from.getId(), from.getBody())).toList();
        return new PostDTO(postFrom.getId(), postFrom.getTitle(), postFrom.getBody(), mapComments);
    }
}
// END
