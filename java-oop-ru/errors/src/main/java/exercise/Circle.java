package exercise;

// BEGIN
public class Circle {
    private final Point center;
    private final Integer radius;

    public Circle(Point center, Integer radius) {
        this.center = center;
        this.radius = radius;
    }

    public Integer getRadius() {
        return radius;
    }

    public Double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("");
        } else {
            return Math.PI * radius * radius;
        }
    }
}
// END
