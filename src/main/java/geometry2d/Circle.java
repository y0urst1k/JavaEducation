package geometry2d;

import exceptions.GeometryException;

import java.io.Serializable;

public class Circle implements Figure, Serializable {
    private static final long serialVersionUID = 1L;

    private final double radius;

    public Circle(double radius) {
        if (!isValidDimension(radius)) {
            throw GeometryException.badParam("radius", "должен быть положительным конечным числом");
        }
        this.radius = radius;
    }

    private static boolean isValidDimension(double d) {
        return Double.isFinite(d) && d > 0;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Circle(radius=" + radius + ")";
    }
}
