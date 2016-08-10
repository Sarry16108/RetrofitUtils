package retrofit;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Created by yanghj on 16/8/10.
 */
public class JsonUtils {
    private static Gson mGson = new Gson();

    public static <T> T readJsonToObj(JsonObject jsonObject, Class<T> tClass) {
        T t = null;

        try {
            t = mGson.fromJson(jsonObject, tClass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;
    }
}
