package com.dao;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
 
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellType;

import com.model.User;
 
public class ExcelFileGenerator {
	/**
	 * 构造器
	 * @param fieldName 结果集的字段名
	 * @param data
	 */
 
	public ExcelFileGenerator() {
		// TODO Auto-generated constructor stub
	}
	
	private final int SPLIT_COUNT = 1500; //Excel每个工作簿的行数
	 
	private ArrayList fieldName = null; //excel标题数据集
 
	private ArrayList fieldData = null; //excel数据内容	
 
	private HSSFWorkbook workBook = null;
 
	/**
	 * 构造器
	 * @param fieldName 结果集的字段名
	 * @param data
	 */
	public ExcelFileGenerator(ArrayList fieldName, ArrayList fieldData) {
 
		this.fieldName = fieldName;
		this.fieldData = fieldData;
	}
 
	/**
	 * 创建HSSFWorkbook对象
	 * @return HSSFWorkbook
	 */
	
	/**
	 * 创建HSSFWorkbook对象
	 * @return HSSFWorkbook
	 */
	public void exportUser(ArrayList<User> list,OutputStream os){
		HSSFWorkbook workbook = new HSSFWorkbook();
<<<<<<< HEAD
		HSSFSheet sheet = workbook.createSheet("家具数据表");
=======
		HSSFSheet sheet = workbook.createSheet("用户数据表");
>>>>>>> dev
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("ID");
		cell = row.createCell(1);
		cell.setCellValue("用户名");
		cell = row.createCell(2);
		cell.setCellValue("密码");
		cell = row.createCell(3);
		cell.setCellValue("性别");
		cell = row.createCell(4);
		cell.setCellValue("安全问题");
		cell = row.createCell(5);
		cell.setCellValue("安全问题答案");
		cell = row.createCell(6);
		cell.setCellValue("邮箱");
		for(int i=0;i<list.size();i++){
			row =sheet.createRow((int)i+1);
			User user = (User) list.get(i);
			row.createCell(0).setCellValue(user.getId());
			row.createCell(1).setCellValue(user.getUsername());
			row.createCell(2).setCellValue(user.getPassword());
			row.createCell(3).setCellValue(user.getSex());
			row.createCell(4).setCellValue(user.getQuestion());
			row.createCell(5).setCellValue(user.getAnswer());
			row.createCell(6).setCellValue(user.getEmail());
		}
		try{
			//FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Ye\\Desktop\\user.xls");
			workbook.write(os);
			os.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
