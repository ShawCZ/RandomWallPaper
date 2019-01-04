package com.shaw.randomwallpaper.base;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.shaw.randomwallpaper.R;
import com.shaw.randomwallpaper.util.LoaderUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author xcz
 * @version 1.0.0
 */
public abstract class PresenterActivity<Presenter extends BaseContract.Presenter>
		extends AppCompatActivity implements BaseContract.View<Presenter> {
	public final String TAG = getClass().getSimpleName();
	protected Presenter mPresenter;
	protected Unbinder mUnbinder;

	//设置图片加载策略
	public static final RequestOptions RECYCLER_OPTIONS =
			new RequestOptions()
					.centerCrop()
					.diskCacheStrategy(DiskCacheStrategy.ALL)
//					.placeholder(R.drawable.place_holder)
					.dontAnimate();

	/**
	 * 初始化Presenter
	 *
	 * @return Presenter
	 */
	protected abstract Presenter initPresenter();

	protected abstract int setLayout();

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(setLayout());
		mUnbinder = ButterKnife.bind(this);
		// 初始化Presenter
		initPresenter();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 界面关闭时进行销毁的操作
		if (mPresenter != null) {
			mPresenter.unSubscribe();
		}
		if (mUnbinder != null) {
			mUnbinder.unbind();
		}
		System.gc();
		System.runFinalization();
	}

	@Override
	public void showLoading(String message) {
		LoaderUtil.showLoading(this, message);
	}

	@Override
	public void stopLoading() {
		LoaderUtil.stopLoading();
	}

	@Override
	public void setPresenter(Presenter presenter) {
		// View中赋值Presenter
		mPresenter = presenter;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void setupToolbar(Toolbar toolbar, String title) {
		setSupportActionBar(toolbar);
		ActionBar ab = getSupportActionBar();
		if (ab != null) {
			ab.setTitle(title);
			ab.setDisplayHomeAsUpEnabled(true);
		}
	}
}
