package com.cv;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cv.http.UploadService;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EnterDemo {

	private static final String ENDPOINT = "http://localhost:8080/MenJin/";

	private static final Retrofit sRetrofit = new Retrofit.Builder().baseUrl(ENDPOINT)
			.addConverterFactory(GsonConverterFactory.create()).build();
	
	

	
	public String httpClientUploadFile(MultipartFile file) {
        final String remote_url = "http://localhost:8080/MenJin/api/enterCheck.do";// 第三方服务器请求地址
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        try {
            String fileName = file.getOriginalFilename();
            HttpPost httpPost = new HttpPost(remote_url);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("file", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, fileName);// 文件流
            builder.addTextBody("filename", fileName);// 类似浏览器表单提交，对应input的name和value
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);// 执行提交
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void testUpload(){
		UploadService service = sRetrofit.create(UploadService.class);

		File file1 = new File("C:\\Users\\user\\Desktop\\Personal\\test4.jpg");

		RequestBody requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), file1);

		Map<String, RequestBody> map = new HashMap<String, RequestBody>();
		map.put("file", requestBody1);
		
		try {
			Call<Gson> result = service.enterCheck(map);
			Response<Gson> rsp = result.execute();
			System.out.println(rsp.isSuccessful());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
