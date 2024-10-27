package exercise;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<List<Post>> getPosts(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        List<Post> body = posts.subList(page * limit, (page + 1) * limit);
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(posts.size()))
                .body(body);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPost(@PathVariable String id) {
        Optional<Post> foundPost = posts.stream().filter(post -> post.getId().equals(id)).findAny();
        return ResponseEntity.of(foundPost);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post newPost) {
        posts.add(newPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@RequestBody Post changedPost, @PathVariable String id) {
        Optional<Post> foundPost = posts.stream().filter(post -> post.getId().equals(id)).findAny();
        if (foundPost.isPresent()) {
            foundPost.get().setTitle(changedPost.getTitle());
            foundPost.get().setBody(changedPost.getBody());
        }
        return ResponseEntity.status(foundPost.isPresent() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(changedPost);
    }
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
