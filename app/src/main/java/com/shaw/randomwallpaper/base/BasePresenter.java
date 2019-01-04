package com.shaw.randomwallpaper.base;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<T extends BaseContract.View> implements BaseContract.Presenter {
	public final String TAG = this.getClass().getSimpleName();

	private T mView;
	public final CompositeDisposable DISPOSABLES;

	public BasePresenter(T view) {
		setView(view);
		DISPOSABLES = new CompositeDisposable();
	}

	/**
	 * 设置一个View，子类可以复写
	 */
	@SuppressWarnings("unchecked")
	protected void setView(T view) {
		this.mView = view;
		this.mView.setPresenter(this);
	}

	/**
	 * 给子类使用的获取View的操作
	 * 不允许复写
	 *
	 * @return View
	 */
	protected final T getView() {
		return mView;
	}

	@Override
	public void subscribe() {
		// 开始的时候进行Loading调用
		T view = mView;
		if (view != null) {
			view.showLoading(null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void unSubscribe() {
		T view = mView;
		mView = null;
		if (view != null) {
			// 把Presenter设置为NULL
			view.setPresenter(null);
		}
		if (DISPOSABLES != null) {
			DISPOSABLES.clear();
		}
	}
}
