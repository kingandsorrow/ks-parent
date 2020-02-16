package top.ks.cy.web.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * excel工具
 * @author limy
 *
 */
public class Excel2003Utils extends ExcelUtils{
	/**
	 * 初始化workbook相关属性
	 */
	public  void initWorkBook(){
		workbook=new HSSFWorkbook();
	}
	
	/**
	 * 初始化导入的workbook
	 * 
	 */
	public void initImportBook(FileInputStream fis) throws IOException{
		workbook=new HSSFWorkbook(fis);
	}
	
}
