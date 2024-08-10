package exercise;

// BEGIN
public class App {

    public static void swapKeyValue(KeyValueStorage storage) {
        storage.toMap().forEach((key, value) -> {
            storage.unset(key);
            storage.set(value, key);
        });
    }
}
// END
