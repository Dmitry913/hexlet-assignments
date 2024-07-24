package exercise;

import lombok.AllArgsConstructor;

import java.util.Locale;

// BEGIN
@AllArgsConstructor
public class Cottage implements Home {

    private double area;
    private int floorCount;

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "%d этажный коттедж площадью %.1f метров", floorCount, getArea());
    }
}
// END
