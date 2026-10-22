package geometry3d;

import exceptions.GeometryException;
import geometry2d.Circle;
import geometry2d.Rectangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CylinderTest {
    private static final double EPSILON = 1e-9;

    @Test
    void volumeOfCylinderWithCircleBase() {
        Circle base = new Circle(2.0);
        Cylinder cylinder = new Cylinder(base, 3.0);
        double expectedVolume = Math.PI * 2.0 * 2.0 * 3.0;
        assertEquals(expectedVolume, cylinder.volume(), EPSILON);
    }

    @Test
    void volumeOfCylinderWithRectangleBase() {
        Rectangle base = new Rectangle(2.0, 3.0);
        Cylinder cylinder = new Cylinder(base, 4.0);
        double expectedVolume = 2.0 * 3.0 * 4.0;
        assertEquals(expectedVolume, cylinder.volume(), EPSILON);
    }

    @Test
    void gettersReturnCorrectValues() {
        Circle base = new Circle(1.0);
        double height = 2.0;
        Cylinder cylinder = new Cylinder(base, height);
        assertSame(base, cylinder.getBase());
        assertEquals(height, cylinder.getHeight(), EPSILON);
    }

    @Test
    void toStringContainsBaseAndHeight() {
        Circle base = new Circle(1.0);
        Cylinder cylinder = new Cylinder(base, 2.0);
        String result = cylinder.toString();
        assertTrue(result.contains("Cylinder"));
        assertTrue(result.contains("Circle"));
        assertTrue(result.contains("height=2.0"));
    }

    @Test
    void constructorThrowsExceptionForNullBase() {
        Assertions.assertThrows(GeometryException.class, () -> new Cylinder(null, 1.0));
    }

    @Test
    void constructorThrowsExceptionForNegativeHeight() {
        Circle base = new Circle(1.0);
        Assertions.assertThrows(GeometryException.class, () -> new Cylinder(base, -1.0));
    }

    @Test
    void constructorThrowsExceptionForInfiniteHeight() {
        Circle base = new Circle(1.0);
        Assertions.assertThrows(GeometryException.class, () -> new Cylinder(base, Double.POSITIVE_INFINITY));
    }
}
