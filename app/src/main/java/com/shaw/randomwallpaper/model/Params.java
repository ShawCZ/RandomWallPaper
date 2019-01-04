package com.shaw.randomwallpaper.model;

import java.util.WeakHashMap;

/**
 * Created on 2018/12/25.
 *
 * @author XCZ
 */
public class Params {
	private final WeakHashMap<String, Object> PARAMS;
	//默认加载第一页
	private int page = 1;
	//默认加载10张图
	private int per_page = 10;
	//默认按日期排序
	private final String order_by = "latest";

	private Params() {
		PARAMS = new WeakHashMap<>();
	}

	public static Params builder() {
		return new Params();
	}

	public Params page(int page) {
		this.page = page;
		return this;
	}

	public Params perPage(int perPage) {
		this.per_page = perPage;
		return this;
	}

	public WeakHashMap<String, Object> build() {
		PARAMS.put("page", page);
		PARAMS.put("per_page", per_page);
		PARAMS.put("order_by", order_by);
		return PARAMS;
	}
}
