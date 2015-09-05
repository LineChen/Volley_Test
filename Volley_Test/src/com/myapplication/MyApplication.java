package com.myapplication;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
	private static RequestQueue queues;//�������
	
	private static Context context;//ȫ��������
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
		queues = Volley.newRequestQueue(context);
		
	}
	
	/**
	 * ��ȡȫ�ֵ������������ 
	 * @return
	 */
	public static RequestQueue getHttpQueues(){
		return queues;
	}
	
	//���ȫ��������
	public static Context getContext(){
		return context;
	}
}















