package com.ssh.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.pojo.Finance;
import com.ssh.pojo.Student;
import com.ssh.service.FianceService;
import com.ssh.service.StudentService;
import com.ssh.util.UploadFile;

@SuppressWarnings("serial")
public class ImportAction extends ActionSupport{
	private File uploadFile;
	private InputStream excelFile;
	private String uploadFileFileName;
	private FianceService fianceService;
	private StudentService studentService;
	private String data;
	Map<String,Object> map = new HashMap<String, Object>();
	
	public String importFianceInto() throws Exception {
		String directory = "/file";
		String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
		File target = UploadFile.Upload(uploadFile, uploadFileFileName,targetDirectory);
		List<Finance> financelist = new ArrayList<>();
		excelFile = new FileInputStream(target);
		Workbook wb = new HSSFWorkbook(excelFile);
		Sheet sheet = wb.getSheetAt(0);
		int rowNum = sheet.getLastRowNum() + 1;
		for (int i = 2; i < rowNum; i++) {
			Finance finance = new Finance();
			Row row = sheet.getRow(i);
			int cellNum = row.getLastCellNum();
			for (int j = 0; j < cellNum; j++) {
				Cell cell = row.getCell(j);
				String cellValue = null;
				switch (cell.getCellType()) { // 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
					case 0:
						cellValue = String
								.valueOf((int) cell.getNumericCellValue());
						break;
					case 1:
						cellValue = cell.getStringCellValue();
						break;
					case 2:
						cellValue = cell.getStringCellValue();
						break;
					case 3:
						cellValue = cell.getStringCellValue();
						break;
					case 4:
						cellValue = cell.getStringCellValue();
						break;
					case 5:
						cellValue = String
								.valueOf((int) cell.getNumericCellValue());
						break;
					
				}
				switch (j) {// 通过列数来判断对应插如的字段
					case 0:
						finance.setFeneeddmoney(Float.parseFloat(cellValue));
						break;
					case 1:
						finance.setFrpractical(Float.parseFloat(cellValue));
						break;
					case 2:
						finance.setFedate(cellValue);
						break;
					case 3:
						finance.setFeway(cellValue);
						break;
					case 4:
						finance.setSfestate(cellValue);
						break;
					case 5:
						finance.setStudentid(Integer.parseInt(cellValue));
						break;
					
				}
			}
			boolean isSave =fianceService.SaveFinance(finance);
			if (isSave) {
				map.put("msg","true");
				data = JSON.toJSONString(map);
				int sid =finance.getStudentid();
				Student stu =studentService.getStudentById(sid);
				stu.setFestate(finance.getSfestate());
				studentService.update(stu);
			} else {
				data = JSON.toJSONString(map);
			}
		}
		return SUCCESS;
	}

	
	public File getUploadFile() {
		return uploadFile;
	}


	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}


	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}


	public String getUploadFileFileName() {
		return uploadFileFileName;
	}


	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}


	public FianceService getFianceService() {
		return fianceService;
	}

	public void setFianceService(FianceService fianceService) {
		this.fianceService = fianceService;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	
	
	
}
