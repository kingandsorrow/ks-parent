package top.ks.cy.web.util;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class Excel2007Utils extends ExcelUtils {
	private static final Integer  CACHEDATA=1000;//keep 100 rows in memory, exceeding rows will be flushed to disk
	/**
	 * 初始化workbook相关属性
	 */
	@Override
	public  void initWorkBook(){
		workbook=new SXSSFWorkbook(CACHEDATA);//内存中保留 1000 条数据，以免内存溢出，其余写入 硬盘 
	}
	
	/**
	 * 初始化导入的workbook
	 * 
	 */
	public void initImportBook(FileInputStream fis) throws IOException{
		workbook=new XSSFWorkbook(fis);
	}
	
	public  void dispose(){
		if(workbook!=null && workbook instanceof SXSSFWorkbook){
			((SXSSFWorkbook)workbook).dispose();// dispose of temporary files backing this workbook on disk 将磁盘上的临时文件清楚
		}
	}
}
