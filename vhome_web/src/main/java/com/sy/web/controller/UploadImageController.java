package com.sy.web.controller;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sy.commons.utils.DateUtil;
import com.sy.commons.utils.FileCreate;
import com.sy.modules.common.GlobalConstants;
import com.sy.modules.entity.sys.SysUser;
import com.sy.modules.utils.DataTool;
import com.sy.modules.utils.ImgUploadUtil;
import com.sy.web.commons.Constants;
import com.sy.web.commons.SessionUtil;


@Controller
public class UploadImageController {
	
	private static final Logger log=LoggerFactory.getLogger(UploadImageController.class);
	//图片上传
	@RequestMapping(value = "/picupload")
	public void bgpicupload(MultipartHttpServletRequest request,HttpServletResponse response, HttpServletRequest req,
			@RequestParam(value = "updateP") String updateP) throws IOException {
		log.info("entering...UploadImageController...picupload()...");
		String fileNameSuffix = null;
		String fileName = null;
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		int width=Integer.parseInt(request.getParameter("width"));
		int height=Integer.parseInt(request.getParameter("height"));
		// 项目在容器中实际发布运行的根路径
		String realPath=request.getSession().getServletContext().getRealPath("/");
		//子文件夹
		SysUser u=SessionUtil.getLoginUser(request);
		String subFile=realPath+GlobalConstants.IMAGE_TEMP+GlobalConstants.SEPARATOR+u.getUsername();
		//String outFile=u.getUsername()+"/"+DateUtil.formatDate(new Date(), DateUtil.MM_DD_YYYY)+"/";		
		//生成唯一文件名
		//SimpleDateFormat simpleFormat = new SimpleDateFormat("MMddHHmmsss");
		//String generationfileName = simpleFormat.format(new Date())+ new Random().nextInt(1000);
		//图片保存临时路径
		String savePath =subFile;
		//创建目录
		FileCreate.createDir(savePath);	
		//文件格式验证
		String picFormat=".jpg.png.gif.bmp";
		if (null != updateP && !"".equals(updateP)) {
			try {
				MultipartFile mf = request.getFile(updateP);
				fileName = mf.getOriginalFilename();
				String finalFilename=Constants.APPIMAGES+u.getUsername()+GlobalConstants.SEPARATOR+DateUtil.formatDate(new Date(), DateUtil.MM_DD_YYYY)+GlobalConstants.SEPARATOR+DataTool.getUUID()+fileName;
				if (null != mf && !"".equals(mf)) {
					fileNameSuffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
					fileNameSuffix=fileNameSuffix.toLowerCase();
					if(picFormat.contains(fileNameSuffix))
					{
						if(mf.getInputStream().available()<=300*1024)
						{
							BufferedImage bi=ImageIO.read(mf.getInputStream());
							if(width==0&&height==0)
							{				
								String path=savePath+GlobalConstants.SEPARATOR+fileName;
								File file=new File(path);
								mf.transferTo(file);
								ImgUploadUtil.upload(path,finalFilename);
								file.delete();
								//返回状态，及文件名
								out.write("{'state':'0','fileName':'" +GlobalConstants.DB_IMAGE_FILE+'/'+ finalFilename + "','updateP':'" + updateP+ "'}");
							}
							else
							{
								if(bi.getWidth()!=width||bi.getHeight()!=height)
								{
									out.write("{'state':'3'}");//图片宽高不符
								}else
								{
									//PictureUtil.SaveFileFromInputStream(mf.getInputStream(), savePath,generationfileName + "." + fileNameSuffix);
									//返回状态，及文件名
									out.write("{'state':'0','fileName':'"+GlobalConstants.DB_IMAGE_FILE +'/'+finalFilename + "','updateP':'" + updateP+ "'}");
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
