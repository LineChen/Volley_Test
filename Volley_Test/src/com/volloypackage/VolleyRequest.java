package com.volloypackage;

import java.util.Map;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.myapplication.MyApplication;

/**
 * Volley的简单的二次回调封装
 * 
 */
public class VolleyRequest {
	private static StringRequest stringRequest;

	public static void RequestGet(Context mContext, String url, String tag,
			VolleyInterface volloeyInterface) {
		// 取消掉该tag的所有请求，防止重复请求
		MyApplication.getHttpQueues().cancelAll(tag);
		stringRequest = new StringRequest(Method.GET, url,
				volloeyInterface.loadingListener(),
				volloeyInterface.errorListener());
		stringRequest.setTag(tag);
		MyApplication.getHttpQueues().add(stringRequest);
		MyApplication.getHttpQueues().start();
	}

	public static void RequestPost(Context mContext, String url, String tag,
			final Map<String, String> params, VolleyInterface volloeyInterface) {
		// 取消掉该tag的所有请求，防止重复请求
		MyApplication.getHttpQueues().cancelAll(tag);
		stringRequest = new StringRequest(url,
				volloeyInterface.loadingListener(),
				volloeyInterface.errorListener()){
			@Override
			protected Map<String, String> getParams()
					throws AuthFailureError {
				return params;
			}
		};
		stringRequest.setTag(tag);
		MyApplication.getHttpQueues().add(stringRequest);
		MyApplication.getHttpQueues().start();
	}
}




















