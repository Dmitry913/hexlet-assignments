package exercise;

import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;
import exercise.model.User;
import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;

import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        app.get("/users/{id}", ctx -> {
            long userId = ctx.pathParamAsClass("id", Long.class).getOrDefault(-1L);
            User foundUser = USERS.stream().filter(elem -> elem.getId() == userId).findAny()
                    .orElseThrow(() -> new NotFoundResponse("User not found"));
            ctx.render("users/show.jte", model("user", new UserPage(foundUser)));
        });
        app.get("/users", ctx -> {
            ctx.render("users/index.jte", model("page", new UsersPage(USERS)));
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
