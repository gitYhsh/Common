package com.juxue.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**  
 * 创建时间：2018年6月1日 上午8:58:42
 * 项目名称：taskmanage  
 * @author yhsh
 * @version 1.0
 */
public class FileUtil {
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	/**多文件上传**/
	public static List<String> excuteFileUpload(MultipartFile []file){
		try {
			String pathInt  = ResourceUtils.getURL("classpath:").getPath();
			File pathall  = new File(pathInt);
			String date = DateUtil.getCurrentDate("yyyy-MM-dd");
			File files = new File(pathInt+"/static/temp/"+date);
			if (!files.exists()){ files.mkdir(); }
			List<String> array = new ArrayList<>();
			for(int i=0;i<file.length;i++){
				String SourceFileName = file[i].getOriginalFilename();
				String filename = SourceFileName.substring(SourceFileName.lastIndexOf("."));
				String path = "static/temp/"+date+"/"+System.currentTimeMillis()+filename;
				File serfile  = new File(pathall.getAbsolutePath(),path);
				file[i].transferTo(serfile);
				array.add(path);
			}
			return array;
		} catch (IOException e) {
			logger.error("文件路径找不到："+e.getMessage());
		}
		return new ArrayList<>();
	}




}
