package com.gowild.retrofit.request;

import com.gowild.retrofit.bean.TestBean;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * @author shuai. wang
 * @since 2016/7/26 18:13
 * @version 1.0
 * <p><strong>Features draft description.主要功能介绍</strong></p>
 */
public interface SimplePostService {
	/**
	 * 简单的post请求
	 * url 指定请求的网址
	 */
	@POST("url")
	Call<TestBean> getDate();

	/**
	 * 简单的post请求
	 * 通过body 直接添加请求体
	 */
	@POST("url")
	Call<TestBean> createUser(@Body TestBean user);


	/**
	 * 简单的post请求
	 * 通过body 直接添加请求体
	 */
	@POST("url")
	Call<TestBean> loadData(@Query("tel") String tel);


	/**
	 * 简单的post请求
	 * 通过body 直接添加请求体 这个是加了请求体
	 */
	@POST("url")
	Call<TestBean> loadData(@Query("tel") String tel,@Body TestBean user);

}
