package exercise;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {
    public static void save(Path path, Car object) throws Exception {
        Files.writeString(path, object.serialize(), StandardOpenOption.CREATE);
    }

    public static Car extract(Path path) throws Exception {
        return Car.deserialize(Files.readString(path));
    }
}
// END
