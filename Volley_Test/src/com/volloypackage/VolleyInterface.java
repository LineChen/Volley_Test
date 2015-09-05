package com.volloypackage;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.myapplication.MyApplication;

public abstract class VolleyInterface {
	private Context context;
	public static Listener<String> mListener;
	public static ErrorListener mErrorListener;

	public VolleyInterface(Context context, Listener<String> mListener,
			ErrorListener mErrorListener) {
		this.context = MyApplication.getContext();
		this.mListener = mListener;
		this.mErrorListener = mErrorListener;
	}
	
	//定义两个回调方法
	public abstract void onMySuccess(String result);
	
	public abstract void onMyError(VolleyError error);
	
	
	public Listener<String> loadingListener(){
		mListener = new Listener<String>() {
			@Override
			public void onResponse(String arg0) {
				//弹出加载中对话框--只用在这里用，而不用在每次加载中都去写这些东西
				onMySuccess(arg0);
			}
		};
		return mListener;
	}
	
	public ErrorListener errorListener(){
		mErrorListener = new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError arg0) {
				onMyError(arg0);
				//提示请求失败
			}
		};
		return mErrorListener;
	}

}












