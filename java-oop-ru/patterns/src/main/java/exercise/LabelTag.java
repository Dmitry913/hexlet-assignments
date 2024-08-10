package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    private static final String PATTERN = "<label>%s%s</label>";
    private final String text;
    private final TagInterface nestedTag;

    public LabelTag(String text, TagInterface nestedTag) {
        this.text = text;
        this.nestedTag = nestedTag;
    }

    @Override
    public String render() {
        return String.format(PATTERN, text, nestedTag.render());
    }
}
// END
