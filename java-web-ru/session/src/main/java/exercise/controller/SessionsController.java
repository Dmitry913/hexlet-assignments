package exercise.controller;

import exercise.dto.LoginPage;
import exercise.dto.MainPage;
import exercise.model.User;
import exercise.repository.UsersRepository;
import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.Optional;

import static io.javalin.rendering.template.TemplateUtil.model;

public class SessionsController {

    // BEGIN
    public static void mainPage(Context ctx) {
        ctx.render("index.jte", model("page", new MainPage(ctx.sessionAttribute("username"))));
    }

    public static void buildLoginForm(Context ctx) {
        ctx.render("build.jte", model("page", new LoginPage(ctx.formParam("name"), null)));
    }

    public static void login(Context ctx) {
        String username = ctx.formParam("name");
        Optional<User> foundUser =
                UsersRepository.findByName(username);
        if (foundUser.isEmpty()) {
            ctx.render("build.jte", model("page", new LoginPage(username, "Wrong username or password")));
            return;
        }
        if (foundUser.get().getPassword().equals(Security.encrypt(ctx.formParam("password")))) {
            ctx.sessionAttribute("username", username);
            ctx.redirect(NamedRoutes.rootPath());
        } else {
            ctx.render("build.jte", model("page", new LoginPage(username, "Wrong username or password")));
        }
    }

    public static void logout(Context ctx) {
        ctx.sessionAttribute("username", null);
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}
