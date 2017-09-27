package com.sy.commons.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
/**
 * properties处理类
 * @author sss
 */
public class PropertiesUtil {

	private Properties props;

	private static final PropertiesUtil instance = new PropertiesUtil();

	public static final PropertiesUtil getInstance() {
		return instance;
	}

	private PropertiesUtil() {
		// String path = System.getProperty("user.dir") + filename;
		InputStream in;
		try {
			in = PropertiesUtil.class.getResourceAsStream("/global.properties");
			props = new Properties();
			props.load(in);
			in.close();
		} catch (FileNotFoundException e1) {
			System.err.println("加载配置文件失败");
		} catch (Throwable e) {
			System.err.println("加载配置文件失败");
		}
	}

	public String getValue(String key) {
		return props.getProperty(key);
	}

	public String getImageHost() {
		String imgDomain = PropertiesUtil.getInstance().getValue("img.domain");
		String imgPort = PropertiesUtil.getInstance().getValue("img.port");
		String imgPath = PropertiesUtil.getInstance().getValue("img.path");
		return imgDomain + ":" + imgPort + "/" + imgPath + "/";
	}

	/**
	 * 本类主要是对config.properties的密码进行修改
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		PropertiesUtil pu = new PropertiesUtil();
		System.out.println(pu.getValue("img.path"));
	}

}