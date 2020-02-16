package top.ks.cy.web.util;

import org.apache.poi.ss.usermodel.CellType;

import java.io.Serializable;

/**
 * 列属性
 * @author limy
 *
 */

public class ExcelField implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	private Boolean isEnum=false;//是否是枚举，如果是可以加备注
	private Boolean isNull=true;//是否容许为空
	private String enumString;//枚举的值
	private CellType cellType;
	private String mappingName;//别名
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Boolean getIsEnum() {
		return isEnum;
	}
	public void setIsEnum(Boolean isEnum) {
		this.isEnum = isEnum;
	}
	public Boolean getIsNull() {
		return isNull;
	}
	public void setIsNull(Boolean isNull) {
		this.isNull = isNull;
	}
	public ExcelField(String fieldName, Boolean isEnum, Boolean isNull,String enumString) {
		super();
		this.fieldName = fieldName;
		this.isEnum = isEnum;
		this.isNull = isNull;
		this.enumString=enumString;
	}
	public ExcelField() {
		super();
	}
	public String getEnumString() {
		return enumString;
	}
	public void setEnumString(String enumString) {
		this.enumString = enumString;
	}
	public CellType getCellType() {
		return cellType;
	}
	public void setCellType(CellType cellType) {
		this.cellType = cellType;
	}
	public String getMappingName() {
		return mappingName;
	}
	public void setMappingName(String mappingName) {
		this.mappingName = mappingName;
	}
}
