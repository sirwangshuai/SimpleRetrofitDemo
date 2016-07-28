package com.gowild.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gowild.retrofit.bean.TestBean;
import com.gowild.retrofit.request.GsonGetService;
import com.gowild.retrofit.request.ParamsGetService;
import com.gowild.retrofit.request.SimpleGetService;
import com.gowild.retrofit.request.SimplePostService;
import com.squareup.okhttp.ResponseBody;

import java.util.LinkedHashMap;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

	public static final String BASE_URL = "http://172.19.1.146:8080/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/**
	 * 简单的get请求
	 * @param view
	 */
	public void simpleGet(View view) {
		//创建retrofit对象
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
//				.addConverterFactory(GsonConverterFactory.create())
				.build();
		//接口的动态代理
		SimpleGetService service = retrofit.create(SimpleGetService.class);
		//括号中的test.json 表示name为test.json 那么请求的网址是GET http://172.19.1.146:8080/test.json
		Call<ResponseBody> call = service.getDate("test.json");
		//call.execute(); 同步的请求这样写
		//异步的请求
		call.enqueue(new Callback<ResponseBody>() {
			//网络访问成功调用,注意是连接上网络都会在调用,404,500依然回调
			@Override
			public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
				System.out.println(response.body().toString());
				//返回结果是hash地址值
				//System.out: com.squareup.okhttp.ResponseBody$1@53539450
			}
			@Override
			public void onFailure(Throwable t) {
			}
		});
	}

	/**
	 * 带参数的get请求
	 */
	public void paramGet(View view) {
		//创建retrofit对象
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.build();
		//接口的动态代理
		ParamsGetService service = retrofit.create(ParamsGetService.class);
		//这次请求的网址是GET : http://172.19.1.146:8080/login?username=sirshuai&password=123
		Call<ResponseBody> call = service.getDate("sirshuai","123");
		//call.execute(); 同步的请求这样写
		//异步的请求
		call.enqueue(new Callback<ResponseBody>() {
			@Override
			public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
				//404页面,但是依然调用
				System.out.println("成功");
			}
			@Override
			public void onFailure(Throwable t) {
				System.out.println("失败");
			}
		});
//		换一种用是querymap
//		LinkedHashMap 能保证参数的顺序不会乱,但是hashMap会,不一定对请求结果产生影响,所以我用来LinkedHashMap
		LinkedHashMap<String,String> map = new LinkedHashMap<>();
		map.put("name","sirshuai");
		map.put("password","123456");
//      http://172.19.1.146:8080/login?name=sirshuai&password=123456
		Call<ResponseBody> mapCall = service.qureyDate(map);
//		加入请求队列,异步请求
		mapCall.enqueue(new Callback<ResponseBody>() {
			@Override
			public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
			}

			@Override
			public void onFailure(Throwable t) {

			}
		});
//		停止请求
//		call.cancel();
	}


	/**
	 * get请求结果
	 * gosn 数据解析
	 */
	public void GsonFormater(View view) {
		//创建retrofit对象
		Retrofit retrofit = new Retrofit.Builder()
				//不加这一句直接报错,无法将json数据转化为bean
				.addConverterFactory(GsonConverterFactory.create())
				.baseUrl(BASE_URL)
				.build();
		//接口的动态代理
		GsonGetService service = retrofit.create(GsonGetService.class);
		//返回bean对象
		Call<TestBean> call = service.getDate("test.json");
		call.enqueue(new Callback<TestBean>() {
			@Override
			public void onResponse(Response<TestBean> response, Retrofit retrofit) {
				TestBean bean = response.body();
				System.out.println(bean.test);
			}

			@Override
			public void onFailure(Throwable t) {

			}
		});
	}

	/**
	 * Post简单请求
	 */
	public void simplePost(View view) {
		//创建retrofit对象
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL + "login")
				//这句一定要加,否则不知道将把数据转化为什么类型,这个将对象是json数据类型
				//这句表示content-type = application/json;
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		//接口的动态代理
		SimplePostService service = retrofit.create(SimplePostService.class);
//		Call<TestBean> date = service.getDate();
		TestBean testBean = new TestBean();
		testBean.test = "test";
		//这个bean将会转换为json数据类型
		Call<TestBean> call = service.createUser(testBean);
		call.enqueue(new Callback<TestBean>() {
			@Override
			public void onResponse(Response<TestBean> response, Retrofit retrofit) {

			}

			@Override
			public void onFailure(Throwable t) {
			}
		});
	}

	/**
	 * Post简单请求
	 */
	public void paramPost(View view) {
		//创建retrofit对象
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL + "login")
				//这句表示content-type = application/json;
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		//接口的动态代理
		SimplePostService service = retrofit.create(SimplePostService.class);
		//这个bean将会转换为json数据类型
		//可以直接@queryMap 丢进去一个map
		Call<TestBean> call = service.loadData("188888888");
		//这个没有加请求体参数,其实就是和get请求差不多了网址是:http://172.19.1.146:8080/url?tel=188888888
		call.enqueue(new Callback<TestBean>() {
			@Override
			public void onResponse(Response<TestBean> response, Retrofit retrofit) {

			}

			@Override
			public void onFailure(Throwable t) {
			}
		});
	}
}
