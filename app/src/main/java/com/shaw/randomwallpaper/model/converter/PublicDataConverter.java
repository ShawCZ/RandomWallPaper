package com.shaw.randomwallpaper.model.converter;

import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shaw.randomwallpaper.model.DataConverter;
import com.shaw.randomwallpaper.model.bean.PublicBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/12/19.
 *
 * @author XCZ
 */
public class PublicDataConverter extends DataConverter<List<PublicBean>> {
	private PublicDataConverter(String data) {
		super(data);
	}

	public static PublicDataConverter with(String data) {
		return new PublicDataConverter(data);
	}

	@Override
	public List<PublicBean> convert() {
//		Log.d(TAG, "convert: data = " + getJsonData());
		List<PublicBean> userList = new ArrayList<>();

		JSONArray jsonArray = JSONArray.parseArray(getJsonData());
		int size = jsonArray.size();

		for (int i = 0; i < size; i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String id = jsonObject.getString("id");
			int width = jsonObject.getIntValue("width");
			int height = jsonObject.getIntValue("height");

			JSONObject urlObject = jsonObject.getJSONObject("urls");
			String url_pic = urlObject.getString("regular");

			userList.add(new PublicBean(id, url_pic, width, height));
		}

		Log.d(TAG, "convert: userList size = " + userList.size());
		return userList;
	}
}
