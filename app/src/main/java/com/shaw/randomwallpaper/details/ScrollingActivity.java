package com.shaw.randomwallpaper.details;

import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.shaw.randomwallpaper.R;
import com.shaw.randomwallpaper.R2;
import com.shaw.randomwallpaper.base.PresenterActivity;
import com.shaw.randomwallpaper.details.DetailContract;
import com.shaw.randomwallpaper.details.DetailPresenter;
import com.shaw.randomwallpaper.model.bean.PublicBean;
import com.shaw.randomwallpaper.util.DimenUtil;
import com.shaw.randomwallpaper.util.glide.GlideApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ScrollingActivity extends PresenterActivity<DetailContract.Presenter> implements DetailContract.View {
	public static final String BEAN = "bean";
	@BindView(R2.id.app_bar)
	AppBarLayout mAppBarLayout = null;
	@BindView(R2.id.toolbar)
	Toolbar toolbar = null;
	@BindView(R2.id.fab)
	FloatingActionButton fab = null;
	@BindView(R2.id.img)
	AppCompatImageView mImage = null;

	private PublicBean bean = null;

	@Override
	protected DetailContract.Presenter initPresenter() {
		return new DetailPresenter(this);
	}

	@Override
	protected int setLayout() {
		return R.layout.activity_scrolling;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setSupportActionBar(toolbar);
		fab.setOnClickListener(view -> Snackbar.make(view, "Set As Wallpaper?", Snackbar.LENGTH_LONG)
				.setAction("Yes", v -> mPresenter.setWallPaper(bean.getUrl(), ScrollingActivity.this)).show());
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			bean = (PublicBean) bundle.get(BEAN);
		}
		adjustImage();

		GlideApp.with(this)
				.applyDefaultRequestOptions(RECYCLER_OPTIONS)
				.load(bean.getUrl())
				.into(mImage);
	}

	private void adjustImage() {
		mImage.setScaleType(ImageView.ScaleType.FIT_CENTER);

		int width = DimenUtil.getScreenWidth()
				- ((ViewGroup.MarginLayoutParams) mAppBarLayout.getLayoutParams()).leftMargin
				- ((ViewGroup.MarginLayoutParams) mAppBarLayout.getLayoutParams()).rightMargin;
		int height = width * bean.getHeight() / bean.getWidth();
		ViewGroup.LayoutParams para = mAppBarLayout.getLayoutParams();
		if (para != null) {
			para.height = height;
			mAppBarLayout.setLayoutParams(para);
		}
	}
}
