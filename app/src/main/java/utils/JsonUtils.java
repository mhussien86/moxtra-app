package utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Utility class for formating input to a valid date string.
 */
public class JsonUtils {


    /**
     * @param jsonObject to be converted to json String
     * @return json string of the json object
     */
    public static String toJson(Object jsonObject) {
        String json = new Gson().toJson(jsonObject);
        return json;
    }

    /**
     * @param jsonString to be converted to json object
     * @param cls        class type that json string will be converted to the object
     * @return json object of the class type that came as a parameter
     */
    public static Object fromJson(String jsonString, Class<?> cls) {
        return new Gson().fromJson(jsonString, cls);
    }

    /**
     * @param jsonString to be converted to any type
     * @param type       class type that json string will be converted to the object
     * @return json object of the class type that came as a parameter
     */
    public static Object fromJson(String jsonString, Type type) {
        return new Gson().fromJson(jsonString, type);
    }

    public static Object fromJson(Object object, Type type) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return new Gson().fromJson(json, type);
    }
}
