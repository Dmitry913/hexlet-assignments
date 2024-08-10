package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag {
    private static final String PATTERN = "<%s%s>";
    private final String name;

    public SingleTag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return String.format(PATTERN, name, getStringAttributes());
    }
}
// END
