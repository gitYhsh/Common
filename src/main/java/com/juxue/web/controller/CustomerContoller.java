package com.juxue.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.juxue.domain.APiResult;
import com.juxue.services.CommonFileUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@Api(tags = "公用接口")
public class CustomerContoller {
	private static final Logger logger  = LoggerFactory.getLogger(CustomerContoller.class);


	@Autowired
	private CommonFileUtil fileUtil;

	@Autowired
	private RabbitTemplate rabbitTemplate;


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

	@PostMapping(value="sned/demo")
	public void sendMqMsgController(){
		String nowDate = String.valueOf(System.currentTimeMillis());

		JSONObject jsonObject = new JSONObject(true);

		jsonObject.put("demo","demo");
		jsonObject.put("data",nowDate);

		Message message = MessageBuilder
				.withBody(jsonObject.toJSONString().getBytes())
				.setContentType(MessageProperties.CONTENT_TYPE_JSON)
				.build();
		rabbitTemplate.setExchange("device.manage.exchange");
		Message message1 = rabbitTemplate.sendAndReceive("device.manage.demo" , message);

		logger.error(new String(message1.getBody()));



		JSONObject jsonObject222 = new JSONObject(true);

		jsonObject222.put("demo","张三");
		jsonObject222.put("data",nowDate);

		Message message2 = MessageBuilder
				.withBody(jsonObject222.toJSONString().getBytes())
				.setContentType(MessageProperties.CONTENT_TYPE_JSON)
				.build();

		Message message12 = this.rabbitTemplate.sendAndReceive("device.manage.exchange","device.excpet.demo" , message2);
		logger.error(new String(message12.getBody()));

	}


}
