package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public List<Post> getPosts(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> limit) {
        Integer pageNumber = page.orElse(0);
        return posts.subList(pageNumber * limit.orElse(10), (pageNumber + 1) * limit.orElse(10));
    }

    @GetMapping("/posts/{id}")
    public Optional<Post> getPost(@PathVariable String id) {
        return posts.stream().filter(post -> post.getId().equals(id)).findAny();
    }

    @PostMapping("/posts")
    public Post createPost(@RequestBody Post newPost) {
        posts.add(newPost);
        return newPost;
    }

    @PutMapping("/posts/{id}")
    public Post updatePost(@RequestBody Post changedPost, @PathVariable String id) {
        Optional<Post> foundPost = posts.stream().filter(post -> post.getId().equals(id)).findAny();
        if (foundPost.isPresent()) {
            foundPost.get().setTitle(changedPost.getTitle());
            foundPost.get().setBody(changedPost.getBody());
        }
        return changedPost;
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable String id) {
        posts.stream().filter(post -> id.equals(post.getId())).findAny().map(post -> posts.remove(post));
    }

    // END
}
