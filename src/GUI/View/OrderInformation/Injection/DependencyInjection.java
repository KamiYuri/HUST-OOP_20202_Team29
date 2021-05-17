package GUI.View.OrderInformation.Injection;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.util.Callback;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

public class DependencyInjection {
    private static final HashMap<Class<?>, Callable<?>> injectionMethods = new HashMap<>();
    private static final ResourceBundle bundle = null;

    public static Parent load(String location) throws IOException {
        FXMLLoader loader = getLoader(location);
        return loader.load();
    }


    public static FXMLLoader getLoader(String location) {
        return new FXMLLoader(
                DependencyInjection.class.getResource(location),
                bundle,
                new JavaFXBuilderFactory(),
                DependencyInjection::constructController);
    }

    private static Object constructController(Class<?> controllerClass) {
        if(injectionMethods.containsKey(controllerClass)) {
            return loadControllerWithSavedMethod(controllerClass);
        } else {
            return loadControllerWithDefaultConstructor(controllerClass);
        }
    }

    private static Object loadControllerWithSavedMethod(Class<?> controller) {
        try {
            return injectionMethods.get(controller).call();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private static Object loadControllerWithDefaultConstructor(Class<?> controller) {
        try {
            return controller.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void addInjectionMethod(Class<?> controller, Callback<Class<?>, Object> method){
        injectionMethods.put(controller, (Callable<?>) method);
    }
    public static void removeInjectionMethod(Class<?> controller){
        injectionMethods.remove(controller);
    }



}