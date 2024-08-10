package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    private static final String PATTERN = "<%s%s>%s%s</%s>";
    private final String name;
    private final String text;
    private final List<Tag> nestedTag;

    public PairedTag(String name, Map<String, String> attributes, String text, List<Tag> nestedTag) {
        this.name = name;
        this.attributes = attributes;
        this.text = text;
        this.nestedTag = nestedTag;
    }

    @Override
    public String toString() {
        StringBuilder tagsString = new StringBuilder();
        nestedTag.forEach(tag -> tagsString.append(tag.toString()));
        return String.format(PATTERN, name, getStringAttributes(), tagsString, text, name);
    }
}
// END
