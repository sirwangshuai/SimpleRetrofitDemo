package com.gowild.retrofit.request;

import com.squareup.okhttp.ResponseBody;

import java.util.Map;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * @author shuai. wang
 * @since 2016/7/26 11:22
 * @version 1.0
 * <p><strong>Features draft description.主要功能介绍</strong></p>
 * @des 待参数的get请求
 * @des 这里的是 网址
 */
public interface ParamsGetService {
	/**
	 * Query　就是网址后面加的参数
	 * @param name 参数
	 * @param password 参数
	 * @return
	 */
	@GET("login")
	Call<ResponseBody> getDate(@Query("username") String name,@Query("password") String password);

	/**
	 * QueryMap 就是把你想加的参数都丢尽hashmap
	 * @param hashMap 要查询的参数
	 * @return
	 */
	@GET("login")
	Call<ResponseBody> qureyDate(@QueryMap Map<String,String> hashMap);
}
