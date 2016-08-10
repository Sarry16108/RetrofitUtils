package retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by yanghj on 16/8/4.
 */
public class MyOkHttpClient {
    private static MyOkHttpClient mInstance = new MyOkHttpClient();
    private OkHttpClient    mOkHttpClient;

    private MyOkHttpClient() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mOkHttpClient = new OkHttpClient.Builder()
        .addInterceptor(logInterceptor)
        .addInterceptor(new HeaderInterceptor())
        .readTimeout(10000, TimeUnit.SECONDS)
        .connectTimeout(10000, TimeUnit.SECONDS)
        .retryOnConnectionFailure(false)
        .build();
    }

    public static MyOkHttpClient getInstance() {
        return mInstance;
    }

    public OkHttpClient create() {
        return mOkHttpClient;
    }
}
