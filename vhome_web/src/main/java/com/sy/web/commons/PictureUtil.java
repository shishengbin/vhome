package com.sy.web.commons;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * 操作文件
 * 
 * @author sss 2013-8-31
 */
public class PictureUtil {
	// 将图片输出到页面
	public static void showImg(HttpServletResponse response, byte[] byt) {
		response.setContentType("image/png;charset=UTF-8");
		if (byt != null) {
			try {
				OutputStream os = response.getOutputStream();
				os.write(byt);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 将图片流保存至磁盘路径
	public static void SaveFileFromInputStream(InputStream stream, String path,
			String filename) throws IOException {
		FileOutputStream fs = new FileOutputStream(path + "\\" + filename);
		byte[] buffer = new byte[1024];
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		stream.close();
	}
}
