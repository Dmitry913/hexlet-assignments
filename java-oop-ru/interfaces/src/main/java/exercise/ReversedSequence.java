package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {

    private final String reversedString;

    public ReversedSequence(String originalString) {
        this.reversedString = new StringBuilder(originalString).reverse().toString();
    }

    @Override
    public int length() {
        return reversedString.length();
    }

    @Override
    public char charAt(int index) {
        return reversedString.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return reversedString.subSequence(start, end);
    }

    @Override
    public String toString() {
        return reversedString;
    }
}
// END
