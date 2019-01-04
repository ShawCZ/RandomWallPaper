package com.shaw.randomwallpaper.util.net;

/**
 * Created on 2018/5/21.
 *
 * @author XCZ
 */
public class ApiHost {
	//HOST变更后，在拦截器里处理baseurl
	public static String HOST = "https://source.unsplash.com/";

//	public static String BASE_URL = "https://unsplash.com/oauth/";

	public static final String BASE_URL = "https://api.unsplash.com/";
	public static final String ACCESS_KEY = "cea6887652f119ed84c893bd22885fd686fa545adc88c477abb30ceab2450a88";
	public static final String WALLPAPERS = "collections/1065976/photos" ;
	public static final String COLLECTIONS = "collections" ;

//	http://lorempixel.com/1600/900
//https://unsplash.it/1600/900?random（国内加载略慢）
//http://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1（必应返回JSON数据，具体百度)
}
