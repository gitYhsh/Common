package com.juxue.web.controller;

import com.juxue.domain.APiResult;
import com.juxue.services.CommonFileUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@Api(tags = "公用接口")
public class CustomerContoller {
	private static final Logger logger  = LoggerFactory.getLogger(CustomerContoller.class);


	@Autowired
	private CommonFileUtil fileUtil;


	@ApiOperation(value = "文件上传(可多选)" )
	@PostMapping(value="file/upload",headers="content-type=multipart/form-data")
	public APiResult fileUploadController(@RequestParam(name="file") MultipartFile[]file){
		if (file.length <= 0 ){return APiResult.error("文件就收失败");}
		MultipartFile files = file[0];
		try {
			String path = fileUtil.uploadFile(files);
			return APiResult.ok(path);
		}catch (Exception e){
			e.printStackTrace();
			logger.error("创建失败:"+e.getMessage());
		}
		return APiResult.error("上传失败");
	}


}
