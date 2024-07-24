package exercise;

// BEGIN
public interface Home extends Comparable<Home> {
    double getArea();

    @Override
    default int compareTo(Home o) {
        return Double.compare(getArea(), o.getArea());
    }
}
// END
