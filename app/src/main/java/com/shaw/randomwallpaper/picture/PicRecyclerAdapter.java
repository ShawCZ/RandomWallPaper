package com.shaw.randomwallpaper.picture;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.shaw.randomwallpaper.R;
import com.shaw.randomwallpaper.base.App;
import com.shaw.randomwallpaper.model.bean.PublicBean;
import com.shaw.randomwallpaper.util.DimenUtil;
import com.shaw.randomwallpaper.util.FilesUtil;
import com.shaw.randomwallpaper.util.WallpaperUtil;
import com.shaw.randomwallpaper.util.callback.CallbackManager;
import com.shaw.randomwallpaper.util.callback.CallbackType;
import com.shaw.randomwallpaper.util.glide.GlideApp;
import com.shaw.randomwallpaper.util.glide.GlideRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import static com.shaw.randomwallpaper.base.PresenterActivity.RECYCLER_OPTIONS;

/**
 * Created on 2018/12/19.
 *
 * @author XCZ
 */
public class PicRecyclerAdapter extends RecyclerView.Adapter<PicRecyclerAdapter.ViewHolder> {
	private static final String TAG = "PicRecyclerAdapter";
	private final List<PublicBean> data = new ArrayList<>();

	public PicRecyclerAdapter() {
	}

	public void setData(List<PublicBean> data) {
		this.data.clear();
		this.data.addAll(data);
	}

	public List<PublicBean> getData() {
		return data;
	}

	@NonNull
	@Override
	public PicRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pic, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull PicRecyclerAdapter.ViewHolder holder, int position) {
		Log.d(TAG, "onBindViewHolder: position = " + position);

		PublicBean bean = data.get(position);
		final AppCompatImageView imageView = holder.mImg;
		final CardView cardView = (CardView) holder.mImg.getParent();

		imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

		int width = DimenUtil.getScreenWidth()
				- ((ViewGroup.MarginLayoutParams) cardView.getLayoutParams()).leftMargin
				- ((ViewGroup.MarginLayoutParams) cardView.getLayoutParams()).rightMargin;
		int height = width * bean.getHeight() / bean.getWidth();
		ViewGroup.LayoutParams para = imageView.getLayoutParams();
		if (para != null) {
			para.height = height;
			imageView.setLayoutParams(para);
		}

		GlideApp.with(imageView.getContext())
				.applyDefaultRequestOptions(RECYCLER_OPTIONS)
				.load(bean.getUrl())
				.into(imageView)
		;

		cardView.setOnLongClickListener(v -> {
			Log.d(TAG, "setOnLongClickListener: ");
			try {
				CallbackManager.getInstance().excute(CallbackType.ITEM_LONG_CLICK, data.get(position));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		});
	}

	@Override
	public int getItemCount() {
		return data.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder {
		private final AppCompatImageView mImg;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			mImg = itemView.findViewById(R.id.img);
		}
	}
}
