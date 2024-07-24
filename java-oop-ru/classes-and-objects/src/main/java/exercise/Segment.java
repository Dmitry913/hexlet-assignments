package exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;

// BEGIN
@Getter
@AllArgsConstructor
public class Segment {
    private Point beginPoint;
    private Point endPoint;

    public Point getMidPoint() {
        return new Point(
                (beginPoint.getX() + endPoint.getX()) / 2,
                (beginPoint.getY() + endPoint.getY()) / 2
        );
    }
}
// END
