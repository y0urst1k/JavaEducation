package geometry2d;

import exceptions.GeometryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RectangleTest {
    private static final double EPSILON = 1e-9;

    @Test
    void areaOfThreeByFourRectangleIsTwelve() {
        Rectangle rectangle = new Rectangle(3.0, 4.0);
        assertEquals(12.0, rectangle.area(), EPSILON);
    }

    @Test
    void perimeterOfThreeByFourRectangleIsFourteen() {
        Rectangle rectangle = new Rectangle(3.0, 4.0);
        assertEquals(14.0, rectangle.perimeter(), EPSILON);
    }

    @Test
    void areaAndPerimeterOfSquare() {
        Rectangle square = new Rectangle(5.0, 5.0);
        assertEquals(25.0, square.area(), EPSILON);
        assertEquals(20.0, square.perimeter(), EPSILON);
    }

    @Test
    void areaAndPerimeterOfRectangleWithDifferentSides() {
        Rectangle rectangle = new Rectangle(2.5, 4.0);
        assertEquals(10.0, rectangle.area(), EPSILON);
        assertEquals(13.0, rectangle.perimeter(), EPSILON);
    }

    @Test
    void constructorThrowsExceptionForZeroWidth() {
        Assertions.assertThrows(GeometryException.class, () -> new Rectangle(0.0, 4.0));
    }

    @Test
    void constructorThrowsExceptionForNegativeHeight() {
        Assertions.assertThrows(GeometryException.class, () -> new Rectangle(3.0, -1.0));
    }

    @Test
    void constructorThrowsExceptionForNaNParameters() {
        Assertions.assertThrows(GeometryException.class, () -> new Rectangle(Double.NaN, 4.0));
    }
}
