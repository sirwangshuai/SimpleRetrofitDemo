package com.gowild.retrofit.request;

import com.gowild.retrofit.bean.TestBean;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * @author shuai. wang
 * @since 2016/7/26 11:22
 * @version 1.0
 * <p><strong>Features draft description.主要功能介绍</strong></p>
 * @des 简单的get请求,最后返回一个json对象
 */
public interface GsonGetService {

	/**
	 * @des 用 {} 是动态获取的意思,这个要在@path中指定 name的数据类型,在实际使用中赋值
	 * @param name
	 * @return
	 */
	@GET("{name}")
	Call<TestBean> getDate(@Path("name") String name);
}
