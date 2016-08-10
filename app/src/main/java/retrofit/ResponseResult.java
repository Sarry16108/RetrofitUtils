package retrofit;

/**
 * Created by yanghj on 16/8/4.
 */
public interface ResponseResult<T> {
    /**
     *
     * @param status whether is successfull
     * @param data  response data
     * @param msg   回应消息
     */
    void onResponse(boolean status, T data, String msg);
}
