package top.ks.cy.web.util;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * 流需要的参数
 * @author limyd
 *
 */
public class StreamParam implements Serializable{
	private static final long serialVersionUID = 1L;
	private String fullFileName;//文件的名称
	private String fileName;//客户端默认的名称
	private ExcelExportData excelExportData;
	private HttpServletResponse response;
	private String fileExtension="xlsx";//文件后缀
	
	public StreamParam() {
		super();
	}


	public StreamParam(String fullFileName, String fileName, ExcelExportData excelExportData,
			HttpServletResponse response) {
		super();
		this.fullFileName = fullFileName;
		this.fileName = fileName;
		this.excelExportData = excelExportData;
		this.response = response;
	}
	
	
	public StreamParam(String fileName, ExcelExportData excelExportData, HttpServletResponse response) {
		super();
		this.fileName = fileName;
		this.excelExportData = excelExportData;
		this.response = response;
	}


	public String getFullFileName() {
		return fullFileName;
	}
	public void setFullFileName(String fullFileName) {
		this.fullFileName = fullFileName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public ExcelExportData getExcelExportData() {
		return excelExportData;
	}
	public void setExcelExportData(ExcelExportData excelExportData) {
		this.excelExportData = excelExportData;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}


	public String getFileExtension() {
		return fileExtension;
	}


	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	

	
}
