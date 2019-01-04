package com.shaw.randomwallpaper.picture;


import com.shaw.randomwallpaper.base.BaseContract;
import com.shaw.randomwallpaper.model.bean.PublicBean;

import java.util.List;

/**
 * Created on 2018/12/17.
 *
 * @author XCZ
 */
public interface PictureContract {
	interface Presenter extends BaseContract.Presenter {
		void getWallPapers();
	}

	interface View extends BaseContract.View<Presenter> {
		void updateUI(List<PublicBean> data);
	}
}
