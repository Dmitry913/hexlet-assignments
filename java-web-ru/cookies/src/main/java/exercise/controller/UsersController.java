package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void show(Context ctx) {
        User user = UserRepository.find(ctx.pathParamAsClass("id", Long.class).get()).orElseThrow(() -> new NotFoundResponse("Пользователя не существует"));
        if (!StringUtils.equals(ctx.cookie("token"), user.getToken())) {
            ctx.redirect(NamedRoutes.buildUserPath());
        }
        ctx.render("users/show.jte", model("page", new UserPage(user)));
    }

    public static void createUser(Context ctx) {
        String firstName = ctx.formParam("firstName");
        String lastName = ctx.formParam("lastName");
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");
        User newUser = new User(firstName, lastName, email, password, Security.generateToken());
        UserRepository.save(newUser);
        ctx.cookie("token", newUser.getToken());
        ctx.redirect(NamedRoutes.userPath(newUser.getId()));
    }
    // END
}
