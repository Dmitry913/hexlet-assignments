package exercise.controller;

import exercise.dto.posts.PostPage;
import exercise.dto.posts.PostsPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public class PostsController {

    // BEGIN
    public static void show(Context context) {
        Long id = context.pathParamAsClass("id", Long.class)
                .get();
        Post post = PostRepository.find(id).orElseThrow(() -> new NotFoundResponse("Page not found"));
        context.render("posts/show.jte", model("page", new PostPage(post)));
    }

    public static void index(Context context) {
        int pageNum = context.queryParamAsClass("page", Integer.class).getOrDefault(1);
        List<Post> findPosts = PostRepository.findAll(pageNum, 5);
        context.render("posts/index.jte", model("page", new PostsPage(findPosts, pageNum)));
    }
    // END
}
