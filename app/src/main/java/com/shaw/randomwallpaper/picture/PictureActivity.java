package com.shaw.randomwallpaper.picture;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.shaw.randomwallpaper.R;
import com.shaw.randomwallpaper.R2;
import com.shaw.randomwallpaper.details.ScrollingActivity;
import com.shaw.randomwallpaper.base.PresenterActivity;
import com.shaw.randomwallpaper.model.bean.PublicBean;
import com.shaw.randomwallpaper.util.callback.CallbackManager;
import com.shaw.randomwallpaper.util.callback.CallbackType;
import com.shaw.randomwallpaper.util.callback.IGlobeCallback;
import com.shaw.randomwallpaper.util.glide.GlideApp;
import com.shaw.randomwallpaper.util.glide.recycler.MyPreloadModelProvider;

import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class PictureActivity extends PresenterActivity<PictureContract.Presenter> implements PictureContract.View {
	@BindView(R2.id.recycler)
	RecyclerView mRecyclerView = null;

	@Override
	protected PictureContract.Presenter initPresenter() {
		return new PicturePresenter(this);
	}

	@Override
	protected int setLayout() {
		return R.layout.activity_picture;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPresenter.getWallPapers();
		addCallback();
	}

	private void addCallback() {
		CallbackManager.getInstance()
				.addCallback(CallbackType.ITEM_LONG_CLICK, (IGlobeCallback<PublicBean>) this::showDetail);
	}

	private void initRecycler(List<PublicBean> data) {
		LinearLayoutManager manager = new LinearLayoutManager(this);
		PicRecyclerAdapter adapter = new PicRecyclerAdapter();
		adapter.setData(data);
		mRecyclerView.setAdapter(adapter);
		mRecyclerView.setLayoutManager(manager);
		mRecyclerView.setRecyclerListener(holder -> {
			PicRecyclerAdapter.ViewHolder photoViewHolder = (PicRecyclerAdapter.ViewHolder) holder;
			GlideApp.with(PictureActivity.this).clear(photoViewHolder.itemView);
		});
		ListPreloader.PreloadSizeProvider sizeProvider = new ViewPreloadSizeProvider();
		ListPreloader.PreloadModelProvider modelProvider = new MyPreloadModelProvider(this, data);
		RecyclerViewPreloader<PublicBean> preloader =
				new RecyclerViewPreloader<PublicBean>(Glide.with(this), modelProvider, sizeProvider, 3);
		mRecyclerView.addOnScrollListener(preloader);
	}

	private void showDetail(PublicBean bean) {
		Bundle bundle = new Bundle();
		bundle.putParcelable(ScrollingActivity.BEAN, bean);
		Intent intent = new Intent(this, ScrollingActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	public void updateUI(List<PublicBean> data) {
		initRecycler(data);
	}
}
