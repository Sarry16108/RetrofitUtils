package retrofit;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yanghj on 16/8/4.
 */
public class RetrofitUtil {
    private final String TAG = "RetrofitUtil";

    private final String SUCCESS = "000000";

    private static RetrofitUtil mInstance;
    private static Retrofit mRetrofit;
    private ArrayMap<String, RequestData> mCalls = new ArrayMap<>(10);

    private static class RequestData {
        private boolean isAutoCancel;   //true表示会自动取消
        private Call<BaseResponseT> call;

        public RequestData(boolean isAutoCancel, Call<BaseResponseT> call) {
            this.isAutoCancel = isAutoCancel;
            this.call = call;
        }

        public boolean isAutoCancel() {
            return isAutoCancel;
        }

        public void setIsAutoCancel(boolean isAutoCancel) {
            this.isAutoCancel = isAutoCancel;
        }

        public Call<BaseResponseT> getCall() {
            return call;
        }

        public void setCall(Call<BaseResponseT> call) {
            this.call = call;
        }
    }


    public static RetrofitUtil getInstance() {
        if (null == mInstance) {
            synchronized (RetrofitUtil.class) {
                mRetrofit = new Retrofit.Builder()
                        .baseUrl(RequestUrls.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(MyOkHttpClient.getInstance().create())
                        .build();

                mInstance = new RetrofitUtil();
            }
        }

        return mInstance;
    }

    public <T> void create(final Context context, final Class<T> type, final String url, ArrayMap<String, String> map, final ResponseResult<T> callback) {
        create(context, type, url, map, callback, true);
    }

    /**
     *
     * @param type
     * @param url
     * @param map
     * @param callback
     * @param isAutoCancel  页面关闭时候，自动取消未请求的调用
     * @param <T>
     */
    public <T> void create(final Context context, final Class<T> type, final String url, ArrayMap<String, String> map, final ResponseResult<T> callback, boolean isAutoCancel) {
        RequestSpecificApi requestApi = mRetrofit.create(RequestSpecificApi.class);
        Call<BaseResponseT> call = requestApi.getData(url, map);
        call.enqueue(new Callback<BaseResponseT>() {
            @Override
            public void onResponse(Call<BaseResponseT> call, Response<BaseResponseT> response) {
                remove(url);

                if (response.isSuccessful() && null != response.body()) {
                    if (null != callback) {
                        if (SUCCESS.equals(response.body().getCode())) {
                            callback.onResponse(true, JsonUtils.readJsonToObj(response.body().getData(), type), response.body().getCode());
                        } else {
                            callback.onResponse(false, null, response.body().getMsg());
                            Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        return;
                    }
                } else {
                    if (null != callback) {
                        callback.onResponse(false, null, null);
                        Toast.makeText(context, "服务器出故障了", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponseT> call, Throwable t) {
                remove(url);

                if (call.isCanceled()) {
                    return;
                }

                if (null != callback) {
                    callback.onResponse(false, null, t.getMessage());
                    Log.e(TAG, t.getMessage());
                }
            }
        });

        add(url, call, isAutoCancel);
    }

    protected void add(String url, Call<BaseResponseT> call, boolean isAutoCancel) {
        Log.i(TAG, "added the url:" + url);
        mCalls.put(url, new RequestData(isAutoCancel, call));
    }

    public void remove(String url) {
        if (TextUtils.isEmpty(url) || !mCalls.containsKey(url)) {
            Log.i(TAG, "url is not exist:" + url);
            return;
        }

        mCalls.remove(url);
    }

    /**
     *
     * @param isAll true代表所有的请求都会被取消，否则只有isAutoCancel会被保留
     */
    public void cancelAllCalls(boolean isAll) {
        for (RequestData data : mCalls.values()) {
            if (isAll || data.isAutoCancel()) {
                data.getCall().cancel();
            }
        }
        mCalls.clear();
    }
}