package com.ssh.action;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ModelUploadAction extends ActionSupport{
	//文件名，要和你下载文件名一致，否則無法找到文件
			private String fileName;
			//要下載文件的目錄
			private String inputPath;
			private String contentType;
			//下載文件的輸入流，直接返回給前臺，会以文件形式下载
			private InputStream ipts;
			
			public String down(){
				return SUCCESS;
			}
			
			public String getFileName() {
				return fileName;
			}
			public void setFileName(String fileName) {
				this.fileName = fileName;
			}
			public String getInputPath() {
				return inputPath;
			}
			public void setInputPath(String inputPath) {
				this.inputPath = inputPath;
			}
			public String getContentType() {
				return contentType;
			}
			public void setContentType(String contentType) {
				this.contentType = contentType;
			}
			public InputStream getIpts() throws Exception {
				String path = ServletActionContext.getServletContext().getRealPath(inputPath);
				FileInputStream fis = new FileInputStream(path+"\\"+fileName);
				return new BufferedInputStream(fis);
			}
			public void setIpts(InputStream ipts) {
				this.ipts = ipts;
			}
			
}
