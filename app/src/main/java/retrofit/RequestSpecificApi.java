package retrofit;

import android.support.v4.util.ArrayMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by yanghj on 16/8/4.
 */
public interface RequestSpecificApi {
    @FormUrlEncoded
    @POST()
    Call<BaseResponseT> getData(@Url String info, @FieldMap ArrayMap<String, String> map);
}
