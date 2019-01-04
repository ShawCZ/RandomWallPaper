package com.shaw.randomwallpaper.details;


import android.content.Context;

import com.shaw.randomwallpaper.base.BaseContract;
import com.shaw.randomwallpaper.model.bean.PublicBean;

import java.util.List;

/**
 * Created on 2018/12/17.
 *
 * @author XCZ
 */
public interface DetailContract {
	interface Presenter extends BaseContract.Presenter {
		void setWallPaper(String url, Context context);

		void downloadWallPaper(String url, Context context);
	}

	interface View extends BaseContract.View<Presenter> {
	}
}
