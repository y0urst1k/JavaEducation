package geometry2d;

import exceptions.GeometryException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;

public class CircleTest {
    private static final double EPSILON = 1e-9;

    @Test
    void areaOfCircleWithRadiusOneIsPi() {
        Circle circle = new Circle(1.0);
        assertEquals(Math.PI, circle.area(), EPSILON);
    }

    @Test
    void perimeterOfCircleWithRadiusTwoIsFourPi() {
        Circle circle = new Circle(2.0);
        assertEquals(4 * Math.PI, circle.perimeter(), EPSILON);
    }

    @Test
    void areaAndPerimeterOfCircleWithRadiusThree() {
        Circle circle = new Circle(3.0);
        assertEquals(9 * Math.PI, circle.area(), EPSILON);
        assertEquals(6 * Math.PI, circle.perimeter(), EPSILON);
    }

    @Test
    void constructorThrowsExceptionForZeroRadius() {
        Assertions.assertThrows(GeometryException.class, () -> new Circle(0.0));
    }

    @Test
    void constructorThrowsExceptionForNegativeRadius() {
        Assertions.assertThrows(GeometryException.class, () -> new Circle(-1.0));
    }

    @Test
    void constructorThrowsExceptionForNaNRadius() {
        Assertions.assertThrows(GeometryException.class, () -> new Circle(Double.NaN));
    }

    @Test
    void constructorThrowsExceptionForInfiniteRadius() {
        Assertions.assertThrows(GeometryException.class, () -> new Circle(Double.POSITIVE_INFINITY));
    }
}
