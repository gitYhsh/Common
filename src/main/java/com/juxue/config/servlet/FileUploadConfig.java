package com.juxue.config.servlet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;


/**
* 创建时间：2018年6月21日 上午11:30:42
* @author yhsh
* @version 1.0
* @since JDK 1.7.0_21
* 类说明  文件上传的配置
*/
@Configuration
public class FileUploadConfig {

//	@Value("${spring.servlet.multipart.max-file-size}")
//	private String maxFileSize;
//	@Value("${spring.servlet.multipart.max-request-size}")
//	private String maxRequestSize;

	@Value("${fdfs.resHost}")
	private String resHost;

	@Value("${fdfs.storagePort}")
	private String storagePort;

	public String getResHost() {
		return resHost;
	}

	public void setResHost(String resHost) {
		this.resHost = resHost;
	}

	public String getStoragePort() {
		return storagePort;
	}

	public void setStoragePort(String storagePort) {
		this.storagePort = storagePort;
	}
}
