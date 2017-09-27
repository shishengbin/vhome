package com.sy.web.controller.wx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sy.web.commons.PictureUtil;

@Controller
@RequestMapping(value ="/wx")
public class WxPicController {
	@RequestMapping(value = "/picupload")
	public void bgpicupload(MultipartHttpServletRequest request,HttpServletResponse response, HttpServletRequest req,
		@RequestParam(value = "filePic") String filePic) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		int width=Integer.parseInt(request.getParameter("width"));
		int height=Integer.parseInt(request.getParameter("height"));
		
		//生成唯一文件名
		SimpleDateFormat simpleFormat = new SimpleDateFormat("MMddHHmmsss");
		String generationfileName = simpleFormat.format(new Date())+ new Random().nextInt(1000);
		// 保存路径
		String savePath = req.getSession().getServletContext().getRealPath("/wx/images");
		String fileNameSuffix = null;
		String fileName = null;
		//文件格式验证
		String picFormat=".jpg.png.gif.bmp";
		if (null != filePic && !"".equals(filePic)) {
			try {
				MultipartFile mf = request.getFile(filePic);
				fileName = mf.getOriginalFilename();
				if (null != mf && !"".equals(mf)) {
					fileNameSuffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
					fileNameSuffix=fileNameSuffix.toLowerCase();
					if(picFormat.contains(fileNameSuffix))
					{
						if(mf.getInputStream().available()<=1024*1024)
						{
							BufferedImage bi=ImageIO.read(mf.getInputStream());
							if(width==0&&height==0)
							{
								PictureUtil.SaveFileFromInputStream(mf.getInputStream(), savePath,generationfileName + "." + fileNameSuffix);
								//返回状态，及文件名
								out.write("{'state':'0','fileName':'" + generationfileName+ "." + fileNameSuffix + "','filePic':'" + filePic+ "'}");
							}
							else
							{
								if(bi.getWidth()!=width||bi.getHeight()!=height)
									out.write("{'state':'3'}");//图片宽高不符
								else
								{
									PictureUtil.SaveFileFromInputStream(mf.getInputStream(), savePath,generationfileName + "." + fileNameSuffix);
									//返回状态，及文件名
									out.write("{'state':'0','fileName':'" + generationfileName+ "." + fileNameSuffix + "','filePic':'" + filePic+ "'}");
								}
							}
						}
						else
							out.write("{'state':'2'}");//图片大小超出
					}
					else
						out.write("{'state':'1'}");//图片格式不对
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
