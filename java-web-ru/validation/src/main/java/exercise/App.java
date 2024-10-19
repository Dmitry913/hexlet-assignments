package exercise;

import exercise.dto.articles.ArticlesPage;
import exercise.dto.articles.BuildArticlePage;
import exercise.model.Article;
import exercise.repository.ArticleRepository;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import io.javalin.rendering.template.JavalinJte;
import io.javalin.validation.ValidationException;

import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/articles", ctx -> {
            List<Article> articles = ArticleRepository.getEntities();
            var page = new ArticlesPage(articles);
            ctx.render("articles/index.jte", model("page", page));
        });

        // BEGIN
        app.get("/articles/build", ctx -> ctx.render("articles/build.jte", model("page", new BuildArticlePage())));

        app.post("/articles", ctx -> {
            try {
                String titleArticle = ctx.formParamAsClass("title", String.class)
                        .check(item -> item.length() >= 2, "Название не должно быть короче двух символов")
                        .check(
                                item -> !ArticleRepository.existsByTitle(item),
                                "Статья с таким названием уже существует"
                        )
                        .get();
                String contentArticle = ctx.formParamAsClass("content", String.class)
                        .check(item -> item.length() >= 10, "Статья должна быть не короче 10 символов")
                        .get();

                ArticleRepository.save(new Article(titleArticle, contentArticle));
                ctx.redirect("/articles");
            } catch (ValidationException e) {
                ctx.status(HttpStatus.UNPROCESSABLE_CONTENT).render(
                        "articles/build.jte",
                        model(
                                "page",
                                new BuildArticlePage(ctx.formParam("title"), ctx.formParam("content"), e.getErrors())
                        )
                );
            }
        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
