package com.shaw.randomwallpaper.util.callback;

import java.util.WeakHashMap;

/**
 * Created on 2018/8/18.
 *
 * @author XCZ
 */
public class CallbackManager {
	private CallbackManager() {
	}

	private static class Holder {
		private static CallbackManager INSTANCE = new CallbackManager();
	}

	public static CallbackManager getInstance() {
		return Holder.INSTANCE;
	}

	private static final WeakHashMap<Object, IGlobeCallback> CALLBACKS = new WeakHashMap<>();

	public CallbackManager addCallback(Object tag, IGlobeCallback callback) {
		CALLBACKS.put(tag, callback);
		return this;
	}

	public IGlobeCallback getCallback(Object tag) {
		return CALLBACKS.get(tag);
	}

	public void excute(Object tag, Object arg) {
		IGlobeCallback callback = getCallback(tag);
		if (callback != null) {
			callback.executeCallback(arg);
		}
	}
}
