package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    public static String postsPath() {
        return "/posts";
    }

    public static String buildPostPath() {
        return "/posts/build";
    }

    public static String postPath(Long id) {
        return postPath(String.valueOf(id));
    }

    public static String postPath(String id) {
        return "/posts/" + id;
    }

    // BEGIN
    public static String editPostPath(String id) {
        return String.format("/posts/%s/edit", id);
    }

    public static String editPostPath(Long id) {
        return editPostPath(String.valueOf(id));
    }

    public static String postsPath(String id) {
        return "/posts/" + id;
    }

    public static String postsPath(Long id) {
        return postsPath(String.valueOf(id));
    }
    // END
}
