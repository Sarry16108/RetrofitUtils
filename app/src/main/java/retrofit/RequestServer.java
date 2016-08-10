package retrofit;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.util.Log;

/**
 * Created by yanghj on 16/8/8.
 */
public class RequestServer {
    private final String TAG = "RequestServer";

    private static RequestServer mInstance = new RequestServer();

    public static RequestServer getInstance() {
        return mInstance;
    }

    public void getFreeMissions2(Context context, String url, String queryType, String version, String deviceType) {

        ArrayMap arrayMap = new ArrayMap(3);
        arrayMap.put("queryType", "1");
        arrayMap.put("version", "2.7");
        arrayMap.put("deviceType", "5");

        RetrofitUtil.getInstance().create(context, FreeMissionItem.class, url, arrayMap, new ResponseResult<FreeMissionItem>() {
            @Override
            public void onResponse(boolean status, FreeMissionItem data, String msg) {
                if (!status) {
                    Log.e("getData", "status:" + status + "  msg:" + msg);
                } else {
                    Log.e("getData", "status:" + status + "  data:" + data.toString() + "  msg:" + msg);
                }

            }
        });
    }

    public void getFreeMissions(Context context, String url, String... args) {
        if (null != args && 0 != args.length % 2) {
            Log.e(TAG, "args is not in pairs");
            return;
        }

        int length = args.length / 2;
        ArrayMap arrayMap = new ArrayMap(length);
        for (int i = 0; i < length; i+=2) {
            arrayMap.put(args[i], args[i+1]);
        }

        RetrofitUtil.getInstance().create(context, FreeMissionItem.class, url, arrayMap, new ResponseResult<FreeMissionItem>() {
            @Override
            public void onResponse(boolean status, FreeMissionItem data, String msg) {
                if (!status) {
                    Log.e("getData", "status:" + status + "  msg:" + msg);
                } else {
                    Log.e("getData", "status:" + status + "  data:" + data.toString() + "  msg:" + msg);
                }

            }
        });
    }
}