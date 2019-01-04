package com.shaw.randomwallpaper.base;

/**
 * Created on 2018/12/8.
 *
 * @author XCZ
 */
public interface BaseContract {
	interface View<T extends Presenter> {
		// 公共的：显示进度条
		void showLoading(String message);

		// 公共的：关闭进度条
		void stopLoading();

		void setPresenter(T presenter);
	}

	interface Presenter {
		//开始订阅
		void subscribe();

		//取消订阅
		void unSubscribe();
	}
}
