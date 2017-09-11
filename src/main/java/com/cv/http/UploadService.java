package com.cv.http;


import java.util.Map;

import com.google.gson.Gson;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface UploadService {

	@Multipart
	@POST("/api/enterCheck.do")
	public Call<Gson> enterCheck(@PartMap Map<String, RequestBody> map);
	
	
	
	@Headers("Accept-Language:zh-CN,zh;q=0.8,en;q=0.6")
	@GET("/api/getCompany.do")
	public Call<Gson> getCompany(@Query("version") Integer version);

}
