package Task1Reflection;

import java.lang.reflect.Method;

public class Invoker {
    public static void invokeAnnotated(Object obj) throws Exception {
        var clazz = obj.getClass();

        for (Method method : clazz.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(Repeat.class)) {
                continue; // без аннотации — пропуск
            }

            Repeat repeat = method.getAnnotation(Repeat.class);
            method.setAccessible(true);

            Object[] args = buildArgs(method);

            for (int i = 0; i < repeat.times(); i++) {
                method.invoke(obj, args);
            }
        }
    }

    private static Object[] buildArgs(Method method) {
        var types = method.getParameterTypes();
        var args = new Object[types.length];

        for (int i = 0; i < types.length; i++) {
            if (types[i] == int.class) {
                args[i] = 10 + i;
            } else if (types[i] == String.class) {
                args[i] = "привет";
            } else if (types[i] == double.class) {
                args[i] = 1.5;
            } else if (types[i] == boolean.class) {
                args[i] = true;
            } else {
                args[i] = null;
            }
        }
        return args;
    }
}
