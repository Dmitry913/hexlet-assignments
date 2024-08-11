package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class Validator {
    public static List<String> validate(Object object) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .filter(field -> {
                    try {
                        field.setAccessible(true);
                        return field.isAnnotationPresent(NotNull.class) && field.get(object) == null;
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(Field::getName)
                .collect(Collectors.toList());
    }

}
// END
