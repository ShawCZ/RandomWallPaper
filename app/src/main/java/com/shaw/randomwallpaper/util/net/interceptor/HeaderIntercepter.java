package com.shaw.randomwallpaper.util.net.interceptor;

import android.util.Log;

import com.shaw.randomwallpaper.util.FilesUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created on 2018/5/15.
 *
 * @author XCZ
 */
public class HeaderIntercepter implements Interceptor {

//	@Override
//	public Response intercept(@NonNull Chain chain) throws IOException {
//		String oldHost = chain.request().url().host();
//		String oldUrl = chain.request().url().toString();
////		String newUrl = oldUrl.replace(oldHost, ApiHost.BASE_URL);
//
//		Log.d("request", "intercept: oldHost = " + oldHost);
//		Log.d("request", "intercept: new url = " + oldUrl);
////
////		Request request = chain.request().newBuilder()
////				.header("Connection", "close")
////				.url(newUrl)
////				.build();
//
//		return chain.proceed(chain.request());
//	}

	@Override
	public Response intercept(Chain chain) throws IOException {
		//获取到response
		Response response = chain.proceed(chain.request());
		//打印响应头
		Log.d("request", "intercept:响应头 = " + response.headers().toString());
		Log.d("request", "intercept:响应头 = " + response.toString());
//		FilesUtil.writeToDisk(response.body().byteStream(),"Test","picture-"+System.currentTimeMillis()+".jpeg");
		return response;
	}
}
