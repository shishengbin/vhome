package com.sy.modules.utils;

import java.io.IOException;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.sy.modules.common.GlobalConstants;

public class ImgUploadUtil {
	// 设置好账号的ACCESS_KEY和SECRET_KEY
	//String ACCESS_KEY = "Access_Key";
	//String SECRET_KEY = "Secret_Key";
	// 要上传的空间
	//String bucketname = Constants.BUCKET_NAME;
	// 上传到七牛后保存的文件名
	//String key = "12345.png";
	// 上传文件的路径
	//String FilePath = "D:/picture/12345.png";
	// 密钥配置
	//Auth auth = Auth.create(Constants.ACCESS_KEY, Constants.SECRET_KEY);
	// 创建上传对象
	//UploadManager uploadManager = new UploadManager();
	// 简单上传，使用默认策略，只需要设置上传的空间名就可以了
	public static String getUpToken() {
		String bucketname = GlobalConstants.BUCKET_NAME;
		Auth auth = Auth.create(GlobalConstants.ACCESS_KEY, GlobalConstants.SECRET_KEY);
		return auth.uploadToken(bucketname);
	}

	public static void upload(String FilePath,String key) throws IOException {
		try {
			UploadManager uploadManager = new UploadManager();
			// 调用put方法上传
			Response res = uploadManager.put(FilePath, key, getUpToken());
			// 打印返回的信息
			//System.out.println(res.bodyString());
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			System.out.println(r.toString());
			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
	}
}
