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
 * Volley�ļ򵥵Ķ��λص���װ
 * 
 */
public class VolleyRequest {
	private static StringRequest stringRequest;

	public static void RequestGet(Context mContext, String url, String tag,
			VolleyInterface volloeyInterface) {
		// ȡ������tag���������󣬷�ֹ�ظ�����
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
		// ȡ������tag���������󣬷�ֹ�ظ�����
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




















