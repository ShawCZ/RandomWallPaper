package com.shaw.randomwallpaper.util.net.rx;

import com.shaw.randomwallpaper.model.bean.PublicBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created on 2018/5/15.
 *
 * @author XCZ
 */
public interface RxRestService {
	@GET("https://source.unsplash.com/random/800x600")
	Flowable<ResponseBody> getPic();

	@GET
	Flowable<String> get(@Url String url, @QueryMap Map<String, Object> params);

	@FormUrlEncoded
	@POST
	Flowable<String> post(@Url String url, @FieldMap Map<String, Object> params);

	@POST
	Flowable<String> postRaw(@Url String url, @Body RequestBody body);
}
