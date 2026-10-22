package exceptions;

public class GeometryException extends RuntimeException {
    public GeometryException(String message) {
        super(message);
    }

    public static GeometryException badParam(String paramName, String reason) {
        return new GeometryException("Некорректный параметр '" + paramName + "': " + reason);
    }
}
