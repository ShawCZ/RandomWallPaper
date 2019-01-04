package com.shaw.randomwallpaper.util.net;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.shaw.randomwallpaper.util.net.interceptor.HeaderIntercepter;
import com.shaw.randomwallpaper.util.net.rx.RxRestService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created on 2018/5/15.
 *
 * @author XCZ
 */
public class RestCreator {

	/**
	 * 构建OkHttp
	 */
	private static final class OKHttpClient {
		private static final int TIME_OUT = 60;
		private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
		private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
		private static SSLContext sc;
		private static final TrustManager[] TRUST_ALL_CERTS = new TrustManager[]{new X509TrustManager() {
			@Override
			public void checkClientTrusted(
					java.security.cert.X509Certificate[] x509Certificates,
					String s) throws java.security.cert.CertificateException {
			}

			@Override
			public void checkServerTrusted(
					java.security.cert.X509Certificate[] x509Certificates,
					String s) throws java.security.cert.CertificateException {
			}

			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return new java.security.cert.X509Certificate[]{};
			}
		}};

		private static SSLContext getSc() {
			try {
				sc = SSLContext.getInstance("TLS");
				sc.init(null, TRUST_ALL_CERTS, new java.security.SecureRandom());
				return sc;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		private static OkHttpClient.Builder addInterceptor() {
			INTERCEPTORS.add(new HeaderIntercepter());
			if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
				for (Interceptor interceptor : INTERCEPTORS) {
					BUILDER.addInterceptor(interceptor);
				}
			}
			return BUILDER;
		}

		private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
				.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
				.readTimeout(TIME_OUT, TimeUnit.SECONDS)
				.hostnameVerifier(new HostnameVerifier() {
					@Override
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				})
				.sslSocketFactory(getSc().getSocketFactory())
				.protocols(Collections.singletonList(Protocol.HTTP_1_1))
				.build();
	}

	/**
	 * 静态内部类Holder单例模式,构建全局Retrofit客户端
	 */
	private static final class RetrofitHolder {
		private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
				.baseUrl(ApiHost.BASE_URL)
				.client(OKHttpClient.OK_HTTP_CLIENT)
				.addConverterFactory(ScalarsConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build();
	}

	//RxJava的service接口
	public static RxRestService getRxRestService() {
		return RxRestServiceHolder.REST_SERVICE;
	}

	private static final class RxRestServiceHolder {
		private static final RxRestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
	}
}
