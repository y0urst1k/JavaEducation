package geometry3d;

import geometry2d.Figure;
import exceptions.GeometryException;

import java.io.Serializable;

public class Cylinder implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Figure base;
    private final double height;

    public Cylinder(Figure base, double height) {
        if (base == null) {
            throw GeometryException.badParam("base", "не может быть null");
        }
        if (!Double.isFinite(height) || height <= 0) {
            throw GeometryException.badParam("height", "должен быть положительным конечным числом");
        }
        this.base = base;
        this.height = height;
    }

    public Figure getBase() {
        return base;
    }

    public double getHeight() {
        return height;
    }

    public double volume() {
        return base.area() * height;
    }

    @Override
    public String toString() {
        return "Cylinder(base=" + base + ", height=" + height + ")";
    }
}
