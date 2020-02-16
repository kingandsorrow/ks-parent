package top.ks.cy.web.util;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 导出所需的数据
 * @author limy
 *
 */
public class ExcelExportData implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String fileName;//excel 的名称

	/**
     * 导出数据 key:String 表示每个Sheet的名称 value:List<?> 表示每个Sheet里的所有数据行
     */
	
    private LinkedHashMap<String, List<Object>> dataMap;

    private LinkedHashMap<String,List<Object>> sumMap;//合计数据
    
    /**
     * 每个Sheet里的顶部大标题
     */
    private String[] titles;

    /**
     * 单个sheet里的数据列标题
     */
    private List<String[]> columnNames;

    /**
     * 单个sheet里每行数据的列对应的对象属性名称
     */
    private List<String[]> fieldNames;
    
    private List<ExcelField[]> fields;//每个列的属性，主要设置

    private LinkedHashMap<String, List<Map<String,Object>>> headersMap;//针对报表表头复杂的业务
    
    
    public List<ExcelField[]> getFields() {
		return fields;
	}

	public void setFields(List<ExcelField[]> fields) {
		this.fields = fields;
	}

	public List<String[]> getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(List<String[]> fieldNames) {
        this.fieldNames = fieldNames;
    }

    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public List<String[]> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String[]> columnNames) {
        this.columnNames = columnNames;
    }

    public LinkedHashMap<String, List<Object>> getDataMap() {
        return dataMap;
    }

    public void setDataMap(LinkedHashMap<String, List<Object>> dataMap) {
        this.dataMap = dataMap;
    }

	public ExcelExportData(LinkedHashMap<String, List<Object>> dataMap, String[] titles, List<String[]> columnNames,
			List<String[]> fieldNames) {
		super();
		this.dataMap = dataMap;
		this.titles = titles;
		this.columnNames = columnNames;
		this.fieldNames = fieldNames;
	}

	public ExcelExportData() {
		super();
	}

	public LinkedHashMap<String, List<Map<String,Object>>> getHeadersMap() {
		return headersMap;
	}

	public void setHeadersMap(LinkedHashMap<String, List<Map<String,Object>>> headersMap) {
		this.headersMap = headersMap;
	}

	public LinkedHashMap<String,List<Object>> getSumMap() {
		return sumMap;
	}

	public void setSumMap(LinkedHashMap<String,List<Object>> sumMap) {
		this.sumMap = sumMap;
	}

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
