package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> homes, int countHome) {
        return homes.stream().sorted().limit(countHome).map(Object::toString).toList();
    }
}
// END
