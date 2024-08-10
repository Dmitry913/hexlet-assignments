package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {
    private static final String PATTERN = " %s=\"%s\"";
    protected Map<String, String> attributes;

    public abstract String toString();

    protected String getStringAttributes() {
        StringBuilder builder = new StringBuilder();
        attributes.forEach((key, value) -> builder.append(String.format(PATTERN, key, value)));
        return builder.toString();
    }
}
// END
