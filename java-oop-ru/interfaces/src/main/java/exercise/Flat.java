package exercise;

import lombok.AllArgsConstructor;

import java.util.Locale;

// BEGIN
@AllArgsConstructor
public class Flat implements Home {

    private double area;
    private double balconyArea;
    private int floor;

    @Override
    public double getArea() {
        return balconyArea + area;
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "Квартира площадью %.1f метров на %d этаже", getArea(), floor);
    }
}
// END
