package exercise;

import exercise.dto.users.UsersPage;
import exercise.model.User;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

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
        app.get("/users", ctx -> {
            String term = ctx.queryParam("term");
            List<User> filterUsers =
                    USERS.stream().filter(item -> StringUtils.startsWithIgnoreCase(item.getFirstName(), term)).toList();
            ctx.render("users/index.jte", model("term", term, "users", new UsersPage(StringUtils.isEmpty(term) ? USERS : filterUsers)));
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
