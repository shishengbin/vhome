package com.sy.commons.utils;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import javax.imageio.ImageIO;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class FileCreate {

	public static boolean createFile(String destFileName) throws IOException {
		File file = new File(destFileName);
		if (file.exists()) {
			// System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");
			FileWriter fw = new FileWriter(file);
			fw.write("");
			fw.close();
			return false;
		}
		if (destFileName.endsWith(File.separator)) {
			// System.out.println("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
			return false;
		}
		// 判断目标文件所在的目录是否存在
		if (!file.getParentFile().exists()) {
			// 如果目标文件所在的目录不存在，则创建父目录
			// System.out.println("目标文件所在目录不存在，准备创建它！");
			if (!file.getParentFile().mkdirs()) {
				// System.out.println("创建目标文件所在目录失败！");
				return false;
			}
		}
		// 创建目标文件
		try {
			if (file.createNewFile()) {
				// System.out.println("创建单个文件" + destFileName + "成功！");
				return true;
			} else {
				// System.out.println("创建单个文件" + destFileName + "失败！");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			// System.out.println("创建单个文件" + destFileName + "失败！" +
			// e.getMessage());
			return false;
		}
	}

	// 创建目录
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			// System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		// 创建目录
		if (dir.mkdirs()) {
			// System.out.println("创建目录" + destDirName + "成功！");
			return true;
		} else {
			// System.out.println("创建目录" + destDirName + "失败！");
			return false;
		}
	}

	/*
	 * public static void main(String[] args){
	 * FileCreate.createDir(com.zj.commons
	 * .entity.Constants.UPLOAD_IMAGE_FILE+"/aaa/bbb");
	 * System.out.println("OK");
	 * 
	 * }
	 */

	public static String createTempFile(String prefix, String suffix,
			String dirName) {
		File tempFile = null;
		if (dirName == null) {
			try {
				// 在默认文件夹下创建临时文件
				tempFile = File.createTempFile(prefix, suffix);
				// 返回临时文件的路径
				return tempFile.getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
				// System.out.println("创建临时文件失败！" + e.getMessage());
				return null;
			}
		} else {
			File dir = new File(dirName);
			// 如果临时文件所在目录不存在，首先创建
			if (!dir.exists()) {
				if (!FileCreate.createDir(dirName)) {
					// System.out.println("创建临时文件失败，不能创建临时文件所在的目录！");
					return null;
				}
			}
			try {
				// 在指定目录下创建临时文件
				tempFile = File.createTempFile(prefix, suffix, dir);
				return tempFile.getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
				// System.out.println("创建临时文件失败！" + e.getMessage());
				return null;
			}
		}
	}

	/**
	 * 读取文本文件内容
	 * 
	 * @param filePathAndName
	 *            带有完整绝对路径的文件名
	 * @param encoding
	 *            文本文件打开的编码方式
	 * @return 返回文本文件的内容
	 */
	public static String readTxt(String filePathAndName, String encoding)
			throws IOException {
		encoding = encoding.trim();
		StringBuffer str = new StringBuffer("");
		String st = "";
		try {
			FileInputStream fs = new FileInputStream(filePathAndName);
			InputStreamReader isr;
			if (encoding.equals("")) {
				isr = new InputStreamReader(fs);
			} else {
				isr = new InputStreamReader(fs, encoding);
			}
			BufferedReader br = new BufferedReader(isr);
			try {
				String data = "";
				while ((data = br.readLine()) != null) {
					data = data.replace(data, "\r\n");
					str.append(data + " ");
				}
			} catch (Exception e) {
				str.append(e.toString());
			}
			st = str.toString();
		} catch (IOException es) {
			st = "";
		}
		return st;
	}

	/**
	 * 将文本内容写入文件
	 * 
	 * @param fileName
	 *            带有完整绝对路径的文件名
	 * @param encoding
	 *            文本文件打开的编码方式
	 * @param towrite
	 *            文本内容
	 */
	public static void SaveStringToFile(String fileName, String towrite,
			String encoding) {
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;

		try {
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
				file = new File(fileName);
			}
			fos = new FileOutputStream(file, true);
			osw = new OutputStreamWriter(fos, encoding);

			osw.write(towrite);
			osw.write("\r\n");
			osw.flush();
		} catch (IOException iex) {
			iex.printStackTrace();
		} finally {
			try {
				if (null != osw)
					osw.close();
				if (null != fos)
					fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void writeByte(String path, byte[] bytes) {
		try {
			FileOutputStream fos;
			fos = new FileOutputStream(path);
			fos.write(bytes);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void resizePng(String srcpPath, String destPath, double d,
			double f) {
		try {
			File resource = new File(srcpPath);
			BufferedImage sourceImage = ImageIO.read(resource);
			BufferedImage dstImage = null;
			AffineTransform transform = AffineTransform.getScaleInstance(d, f);// 返回表示缩放变换的变换
			AffineTransformOp op = new AffineTransformOp(transform,
					AffineTransformOp.TYPE_BILINEAR);
			dstImage = op.filter(sourceImage, null);
			ImageIO.write(dstImage, "png", new File(destPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 图片处理 byte[]参数
	public static void compressPic(byte[] imageByte, String destPath,
			double rate) {
		try {
			ByteArrayInputStream resource = new ByteArrayInputStream(imageByte);
			Image img = ImageIO.read(resource);
			// 判断图片格式是否正确
			int newWidth;
			int newHeight;
			// 判断是否是等比缩放
			// 为等比缩放计算输出的图片宽度及高度
			// 根据缩放比率大的进行缩放控制
			newWidth = (int) (((double) img.getWidth(null)) / rate);
			newHeight = (int) (((double) img.getHeight(null)) / rate);
			BufferedImage tag = new BufferedImage((int) newWidth,
					(int) newHeight, BufferedImage.TYPE_INT_RGB);
			/*
			 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
			 */
			tag.getGraphics().drawImage(
					img.getScaledInstance(newWidth, newHeight,
							Image.SCALE_SMOOTH), 0, 0, null);
			FileOutputStream out = new FileOutputStream(destPath);
			// JPEGImageEncoder可适用于其他图片类型的转换
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(tag);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// 图片处理 intputstream参数
	public static void compressPicInputStream(InputStream is, String destPath,
			double rate) {
		try {
			Image img = ImageIO.read(is);
			// 判断图片格式是否正确
			int newWidth;
			int newHeight;
			// 判断是否是等比缩放
			// 为等比缩放计算输出的图片宽度及高度
			// 根据缩放比率大的进行缩放控制
			newWidth = (int) (((double) img.getWidth(null)) / rate);
			newHeight = (int) (((double) img.getHeight(null)) / rate);
			BufferedImage tag = new BufferedImage((int) newWidth,
					(int) newHeight, BufferedImage.TYPE_INT_RGB);
			/*
			 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
			 */
			tag.getGraphics().drawImage(
					img.getScaledInstance(newWidth, newHeight,
							Image.SCALE_SMOOTH), 0, 0, null);
			FileOutputStream out = new FileOutputStream(destPath);
			// JPEGImageEncoder可适用于其他图片类型的转换
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(tag);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			// 递归删除目录中的子目录下
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}
}