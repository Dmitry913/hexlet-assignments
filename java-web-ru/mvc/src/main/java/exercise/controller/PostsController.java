package exercise.controller;

import exercise.dto.posts.BuildPostPage;
import exercise.dto.posts.EditPostPage;
import exercise.dto.posts.PostPage;
import exercise.dto.posts.PostsPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;

import static io.javalin.rendering.template.TemplateUtil.model;

public class PostsController {

    public static void build(Context ctx) {
        var page = new BuildPostPage();
        ctx.render("posts/build.jte", model("page", page));
    }

    public static void create(Context ctx) {
        try {
            var name = validNewPostName(ctx);

            var body = validNewPostBody(ctx);

            var post = new Post(name, body);
            PostRepository.save(post);
            ctx.redirect(NamedRoutes.postsPath());

        } catch (ValidationException e) {
            var name = ctx.formParam("name");
            var body = ctx.formParam("body");
            var page = new BuildPostPage(name, body, e.getErrors());
            ctx.render("posts/build.jte", model("page", page)).status(422);
        }
    }

    public static void index(Context ctx) {
        var posts = PostRepository.getEntities();
        var postPage = new PostsPage(posts);
        ctx.render("posts/index.jte", model("page", postPage));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }

    // BEGIN
    public static void edit(Context ctx) {
        Post post = getPostById(ctx);
        ctx.render("posts/edit.jte", model("page", new EditPostPage(post.getId(), post.getName(), post.getBody(), null)));
    }

    public static void update(Context ctx) {
        Post post = getPostById(ctx);
        try {
            var name = validNewPostName(ctx);
            var body = validNewPostBody(ctx);
            post.setName(name);
            post.setBody(body);
            ctx.redirect(NamedRoutes.postsPath());
        } catch (ValidationException e) {
            var name = ctx.formParam("name");
            var body = ctx.formParam("body");
            var page = new EditPostPage(post.getId(), name, body, e.getErrors());
            ctx.render("posts/edit.jte", model("page", page)).status(422);
        }
    }

    private static String validNewPostName(Context ctx) {
        return ctx.formParamAsClass("name", String.class)
                .check(value -> value.length() >= 2, "Название не должно быть короче двух символов")
                .get();
    }

    private static String validNewPostBody(Context ctx) {
        return ctx.formParamAsClass("body", String.class)
                .check(value -> value.length() >= 10, "Пост должен быть не короче 10 символов")
                .get();
    }

    private static Post getPostById(Context ctx) {
        return PostRepository.find(ctx.pathParamAsClass("id", Long.class).get())
                .orElseThrow(() -> new NotFoundResponse("Не существует поста с таким id"));
    }
    // END
}
