package retrofit;

import com.google.gson.JsonObject;

/**
 * Created by yanghj on 16/8/4.
 */
public class BaseResponseT {
    private String retCode;
    private String retMsg;
    protected JsonObject data;

    public String getMsg() {
        return retMsg;
    }

    public String getCode() {
        return retCode;
    }

    public JsonObject getData() {
        return data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code='" + retCode + '\'' +
                ", msg='" + retMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
