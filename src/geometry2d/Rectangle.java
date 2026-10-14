package geometry2d;

import exceptions.GeometryException;

import java.io.Serializable;

public class Rectangle implements Figure, Serializable {
    private static final long serialVersionUID = 1L;

    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        if (!isValidDimension(width) || !isValidDimension(height)) {
            String msg = (!isValidDimension(width) ? "width" : "height") + " должен быть положительным конечным числом";
            throw GeometryException.badParam(!isValidDimension(width) ? "width" : "height", msg);
        }
        this.width = width;
        this.height = height;
    }

    private static boolean isValidDimension(double d) {
        return Double.isFinite(d) && d > 0;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public double perimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return "Rectangle(width=" + width + ", height=" + height + ")";
    }
}
