package com.beiing.volley_test;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.myapplication.MyApplication;
import com.volloypackage.VolleyRequest;
import com.volloypackage.VolleyInterface;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * 1.Volley 的get和post请求方式的使用 2.Volley的网络请求队列建立和取消队列请求 3.Volley与Activity生命周期的联动
 * 4.Volley的简单的二次回调封装
 * 
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		 volley_Get();

//		volley_Post();

	}

	// 实现Volley与Activity生命周期的联动
	@Override
	protected void onStop() {
		super.onStop();
		MyApplication.getHttpQueues().cancelAll("abcGet");// 取消该标签的所有请求
		MyApplication.getHttpQueues().cancelAll("abcPost");
	}

	/**
	 * post方式请求数据
	 */
	private void volley_Post() {
		String url = "http://www.2345.com/?";
		// StringRequest request = new StringRequest(Method.POST, url,
		// new Listener<String>() {
		// @Override
		// public void onResponse(String result) {
		// Toast.makeText(MyApplication.getContext(), result, 0).show();
		// }
		// },
		// new Response.ErrorListener() {
		// @Override
		// public void onErrorResponse(VolleyError error) {
		// //输出请求失败的原因
		// Toast.makeText(MyApplication.getContext(), error.toString(),
		// 0).show();
		// }
		// }){
		// //post方式请求的，需要重写该方法
		// @Override
		// protected Map<String, String> getParams()
		// throws AuthFailureError {
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("username", "fei");
		// map.put("pwd", "123");
		// return map;
		// }
		// };
		// request.setTag("abcPost");
		// MyApplication.getHttpQueues().add(request);
		// -------------------------
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "fei");
		map.put("pwd", "123");
		JSONObject object = new JSONObject(map);
		JsonObjectRequest request = new JsonObjectRequest(Method.POST, url,
				object, new Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject result) {
						// ...
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// ...
					}
				});
		request.setTag("abcPost");
		MyApplication.getHttpQueues().add(request);
	}

	/**
	 * get方式请求数据
	 */
	private void volley_Get() {
		String url = "http://www.2345.com/?k1998884";
		// -----StringRequest
		// StringRequest request = new StringRequest(Method.GET, url,
		// new Listener<String>() {
		// @Override
		// public void onResponse(String result) {
		// Toast.makeText(MyApplication.getContext(), result, 0).show();
		// }
		// },
		// new Response.ErrorListener() {
		// @Override
		// public void onErrorResponse(VolleyError error) {
		// //输出请求失败的原因
		// Toast.makeText(MyApplication.getContext(), error.toString(),
		// 0).show();
		// }
		// });
		//
		// request.setTag("abcGet");
		// MyApplication.getHttpQueues().add(request);
		// --------------------------------------
		// 第三个参数说明，因为这里是get方式，url已经在外面定义好了，如果是post方式，就要传一个包含url信息的JSON对象
		// JsonObjectRequest request = new JsonObjectRequest(Method.GET, url,
		// null, new Listener<JSONObject>() {
		// @Override
		// public void onResponse(JSONObject result) {
		// //...
		// }
		// }, new Response.ErrorListener() {
		//
		// @Override
		// public void onErrorResponse(VolleyError error) {
		// //...
		// }
		// });
		// request.setTag("abcGet");
		// MyApplication.getHttpQueues().add(request);

		// 使用二次封装
		VolleyRequest.RequestGet(MyApplication.getContext(), url, "abcGet",
				new VolleyInterface(MyApplication.getContext(),
						VolleyInterface.mListener,
						VolleyInterface.mErrorListener) {
			
					@Override
					public void onMySuccess(String result) {
						Log.i("--", "result:" + result);
					}

					@Override
					public void onMyError(VolleyError error) {
						Log.i("--", "error:" + error.toString());
					}
				});

	}
}


















