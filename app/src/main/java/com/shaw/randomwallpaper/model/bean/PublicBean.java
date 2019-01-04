
package com.shaw.randomwallpaper.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author shaw
 */
public class PublicBean implements Parcelable {
	private String id;
	private String url;
	private String url_download;
	private int width;
	private int height;

	public PublicBean(String id, String url, int width, int height) {
		this.id = id;
		this.url = url;
		this.width = width;
		this.height = height;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {

		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(url);
		dest.writeString(url_download);
		dest.writeInt(width);
		dest.writeInt(height);
	}


	protected PublicBean(Parcel in) {
		id = in.readString();
		url = in.readString();
		url_download = in.readString();
		width = in.readInt();
		height = in.readInt();
	}

	public static final Creator<PublicBean> CREATOR = new Creator<PublicBean>() {
		@Override
		public PublicBean createFromParcel(Parcel in) {
			return new PublicBean(in);
		}

		@Override
		public PublicBean[] newArray(int size) {
			return new PublicBean[size];
		}
	};
}