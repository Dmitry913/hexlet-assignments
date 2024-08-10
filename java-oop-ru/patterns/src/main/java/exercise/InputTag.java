package exercise;

// BEGIN
public class InputTag implements TagInterface {
    private static final String PATTERN = "<input type=\"%s\" value=\"%s\">";
    private final String type;
    private final String value;

    public InputTag(String type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String render() {
        return String.format(PATTERN, type, value);
    }
}
// END
