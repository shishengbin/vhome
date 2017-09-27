package com.sy.commons.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import com.sy.commons.entity.HResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * @author sss
 */
public class FTPClientUtil {
	private final static Log LOGGER = LogFactory.getLog(FTPClientUtil.class);
	private static ThreadLocal<Map<String, String>> FTP_CLINET_INFO = new ThreadLocal<Map<String, String>>();
	public static void initFTPClientInfo(Map<String, String> clientInfo) {
		FTP_CLINET_INFO.set(clientInfo);
	}

	public static void connect(FTPClient client) {
		PropertiesUtil pp = PropertiesUtil.getInstance();
		String host = pp.getValue("ftp.host");
		int port = Integer.valueOf(pp.getValue("ftp.port")).intValue();
		String userName = pp.getValue("ftp.username");
		String pwd = pp.getValue("ftp.password");

		LOGGER.info(">>>Start to connect FTP via host[" + host + "], port["+ port + "]");
		if (!client.isConnected()) {
			try {
				client.connect(host, port);
				client.login(userName, pwd);
				client.setFileType(FTPClient.BINARY_FILE_TYPE);
				LOGGER.info(">>>FTP connected successfully via host[" + host+ "], port[" + port + "]");
			} catch (Exception ex) {
				LOGGER.error(">>>FTP connected failed via host[" + host+ "], port[" + port + "]");
				LOGGER.error(">>>FTP error occurred:" + ex);
				ex.printStackTrace();
			}
		} else {
			LOGGER.info(">>>FTP already under connected status via host["+ host + "], port[" + port + "]");
		}
	}

	public static void disconnect(FTPClient client) {
		if (client.isConnected()) {
			try {
				client.disconnect();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static boolean uploadFile(InputStream stream, String fileName,String dist) {
		boolean success = false;
		FTPClient ftpClient = new FTPClient();
		connect(ftpClient);
		try {
			boolean distExist = ftpClient.changeWorkingDirectory(dist);
			if (!distExist) {
				createDirecroty(ftpClient, dist);
				ftpClient.changeWorkingDirectory(dist);
				LOGGER.info(">>>Make and change working directory to:" + dist);
			} else {
				LOGGER.info(">>>Successfully change working directory to:"+ dist);
			}
			if (stream != null) {
				success = ftpClient.storeFile(fileName, stream);
				stream.close();
			}
			success = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnect(ftpClient);
		}
		return success;
	}

	public static boolean uploadFiles(List<String> uploadFiles, String source,String dist) {
		boolean success = false;
		FTPClient ftpClient = new FTPClient();
		connect(ftpClient);
		try {
			boolean distExist = ftpClient.changeWorkingDirectory(dist);
			if (!distExist) {
				createDirecroty(ftpClient, dist);
				ftpClient.changeWorkingDirectory(dist);
				LOGGER.info(">>>Make and change working directory to:" + dist);
			} else {
				LOGGER.info(">>>Successfully change working directory to:"+ dist);
			}
			for (String fileName : uploadFiles) {
				if (fileName == null) {
					continue;
				}
				File file = new File(source + "/" + fileName);
				FileInputStream input = new FileInputStream(file);
				success = ftpClient.storeFile(fileName, input);
				input.close();
				file.delete();
				LOGGER.info(">>>File uploaded:" + file.getAbsolutePath());
			}
			success = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disconnect(ftpClient);
		}
		return success;
	}

	public static boolean createDirecroty(FTPClient ftp, String remote)throws IOException {
		boolean success = true;
		String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
		if (!directory.equalsIgnoreCase("/")
				&& !ftp.changeWorkingDirectory(new String(directory))) {
			int start = 0;
			int end = 0;
			if (directory.startsWith("/")) {
				start = 1;
			} else {
				start = 0;
			}
			end = directory.indexOf("/", start);
			while (true) {
				String subDirectory = new String(remote.substring(start, end));
				if (!ftp.changeWorkingDirectory(subDirectory)) {
					if (ftp.makeDirectory(subDirectory)) {
						ftp.changeWorkingDirectory(subDirectory);
					} else {
						LOGGER.info(">>>createdirectory fail!");
						//System.out.println("创建目录失败");
						success = false;
						return success;
					}
				}
				start = end + 1;
				end = directory.indexOf("/", start);
				if (end <= start) {
					break;
				}
			}
		}
		return success;
	}

	public static boolean deleteFile(String path) {
		FTPClient ftpClient = new FTPClient();
		connect(ftpClient);
		try {
			String imgPath = PropertiesUtil.getInstance().getValue("img.path");
			return ftpClient.deleteFile(imgPath + "/" + path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static HResult uploadFileToFtp(InputStream inputStream,String originalName, String userName, String model) {
		HResult hResult = new HResult(true, "");
		// 生成唯一文件名
		String subFile = userName + "/" + model;
		subFile = subFile + "/"+ DateUtil.formatDate(new Date(), DateUtil.DATEFORMAT);
		SimpleDateFormat simpleFormat = new SimpleDateFormat("MMddHHmmsss");
		// 图片保存路径
		String imgPath = PropertiesUtil.getInstance().getValue("img.path");
		String savePath = imgPath + "/" + subFile;
		try {
			String fileNameSuffix = null;
			boolean checkOk = true;
			// 文件格式验证
			String picFormat = ".jpg.png.gif.bmp,jpeg";
			if (null != originalName && !"".equals(originalName)) {
				fileNameSuffix = originalName.substring(originalName.lastIndexOf(".") + 1,originalName.length());
				fileNameSuffix = fileNameSuffix.toLowerCase();
				if (!picFormat.contains(fileNameSuffix)) {
					hResult.setResult(false);
					hResult.setValue("图片格式不对");
					checkOk = false;
				}
				if (inputStream.available() > 5 * 1024 * 1024) {
					hResult.setResult(false);
					hResult.setValue("图片大小超出");
					checkOk = false;
				}
			}
			if (checkOk) {
				String generationFileName = simpleFormat.format(new Date())+ new Random().nextInt(1000);
				String filePath = subFile + "/" + generationFileName + "."+ fileNameSuffix;
				FTPClientUtil.uploadFile(inputStream, generationFileName + "."+ fileNameSuffix, "/" + savePath + "/");
				hResult.setObjValue(filePath);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			hResult.setResult(false);
			hResult.setValue("图片上传异常,请稍后再试!");
		}
		return hResult;
	}

	public static HResult uploadFileToFtp(List<MultipartFile> imageFiles,String realPath, String userName, String model) {
		HResult hResult = new HResult(true, "");
		// 生成唯一文件名
		String subFile = userName + "/" + model;
		subFile = subFile + "/"
				+ DateUtil.formatDate(new Date(), DateUtil.DATEFORMAT);
		SimpleDateFormat simpleFormat = new SimpleDateFormat("MMddHHmmsss");
		// 图片保存路径
		String imgPath = PropertiesUtil.getInstance().getValue("img.path");
		String savePath = imgPath + "/" + subFile;
		try {
			String fileNameSuffix = null;
			String fileName = null;
			boolean checkOk = true;
			// 文件格式验证
			String picFormat = ".jpg.png.gif.bmp,jpeg";
			if (null != imageFiles && imageFiles.size() > 0) {
				for (MultipartFile imageFile : imageFiles) {
					fileName = imageFile.getOriginalFilename();
					if (null != fileName && !"".equals(fileName)) {
						fileNameSuffix = fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length());
						fileNameSuffix = fileNameSuffix.toLowerCase();
						if (!picFormat.contains(fileNameSuffix)) {
							hResult.setResult(false);
							hResult.setValue("图片格式不对");
							checkOk = false;
							break;
						}

						if (imageFile.getInputStream().available() > 5 * 1024 * 1024) {
							hResult.setResult(false);
							hResult.setValue("图片大小超出");
							checkOk = false;
							break;
						}
					}
				}
				if (checkOk) {
					List<String> uploadFiles = new ArrayList<String>(0);
					Map<String, String> uploadPathMap = new HashMap<String,String>(0);
					// List<String> uploadPath = new ArrayList<>(0);
					String generationfileName;
					for (MultipartFile imageFile : imageFiles) {
						generationfileName = simpleFormat.format(new Date())+ new Random().nextInt(1000);
						File localFile = new File(realPath + "/"+ generationfileName + "." + fileNameSuffix);
						FileCopyUtils.copy(imageFile.getBytes(), localFile);
						// PictureUtil.SaveFileFromInputStream(imageFile.getInputStream(),
						// realPath, generationfileName + "." + fileNameSuffix);
						// 添加需要保存的图片
						uploadFiles.add(generationfileName + "."+ fileNameSuffix);
						String filePath = subFile + "/" + generationfileName+ "." + fileNameSuffix;
						// uploadPath.add(filePath);
						uploadPathMap.put(imageFile.getName(), filePath);
					}

					FTPClientUtil.uploadFiles(uploadFiles, realPath, "/"+ savePath + "/");
					hResult.setObjValue(uploadPathMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			hResult.setResult(false);
			hResult.setValue("图片上传异常,请稍后再试!");
		}
		return hResult;
	}

	public static HResult uploadVideoToFtp(List<MultipartFile> videoFiles,String realPath, String userName, String model) {
		HResult hResult = new HResult(true, "");
		// 生成唯一文件名
		String subFile = userName + "/" + model;
		subFile = subFile + "/"+ DateUtil.formatDate(new Date(), DateUtil.DATEFORMAT);
		SimpleDateFormat simpleFormat = new SimpleDateFormat("MMddHHmmsss");
		// 视频保存路径
		String videoPath = PropertiesUtil.getInstance().getValue("img.path");
		String savePath = videoPath + "/" + subFile;
		try {
			String fileNameSuffix = null;
			String fileName = null;
			boolean checkOk = true;
			// 文件格式验证
			String picFormat = ".avi.rmvb.rm.asf.divx.mpg.mpeg.mpe.wmv.mp4.mkv.vob ";
			if (null != videoFiles && videoFiles.size() > 0) {
				for (MultipartFile videoFile : videoFiles) {
					fileName = videoFile.getOriginalFilename();
					if (null != fileName && !"".equals(fileName)) {
						fileNameSuffix = fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length());
						fileNameSuffix = fileNameSuffix.toLowerCase();
						if (!picFormat.contains(fileNameSuffix)) {
							hResult.setResult(false);
							hResult.setValue("视频格式不对");
							checkOk = false;
							break;
						}
						if (videoFile.getInputStream().available() > 20 * 1024 * 1024) {
							hResult.setResult(false);
							hResult.setValue("视频大小超出");
							checkOk = false;
							break;
						}
					}
				}
				if (checkOk) {
					List<String> uploadFiles = new ArrayList<String>(0);
					Map<String, String> uploadPathMap = new HashMap<String,String>(0);
					// List<String> uploadPath = new ArrayList<>(0);
					String generationfileName;
					for (MultipartFile videoFile : videoFiles) {
						generationfileName = simpleFormat.format(new Date())+ new Random().nextInt(1000);
						File localFile = new File(realPath + "/"+ generationfileName + "." + fileNameSuffix);
						FileCopyUtils.copy(videoFile.getBytes(), localFile);
						// PictureUtil.SaveFileFromInputStream(imageFile.getInputStream(),
						// realPath, generationfileName + "." + fileNameSuffix);
						// 添加需要保存的图片
						uploadFiles.add(generationfileName + "."+ fileNameSuffix);
						String filePath = subFile + "/" + generationfileName+ "." + fileNameSuffix;
						// uploadPath.add(filePath);
						uploadPathMap.put(videoFile.getName(), filePath);
					}
					FTPClientUtil.uploadFiles(uploadFiles, realPath, "/"+ savePath + "/");
					hResult.setObjValue(uploadPathMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			hResult.setResult(false);
			hResult.setValue("视频上传异常,请稍后再试!");
		}
		return hResult;
	}

	public static void main(String[] args) throws Exception {
		List<String> uploadFiles = new ArrayList<String>(0);
		uploadFiles.add("12345.png");
		// E:\\my
		// project\\Evergrande\\HengDaRuYe\\trunk\\movite\\applications\\admin\\portal\\web\\target\\admin-portal-web-0.1.0\\
		// /dairy/user/product/2015-06-11/
		uploadFiles(uploadFiles, "D:\\picture\\", "/dairy/user/");
	}
}
