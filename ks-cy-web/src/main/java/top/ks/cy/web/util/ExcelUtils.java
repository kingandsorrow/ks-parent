package top.ks.cy.web.util;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.usermodel.*;
import top.ks.common.util.Strings;

import java.io.*;
import java.util.*;

public class ExcelUtils {
    private static final Log logger = LogFactory.getLog(ExcelUtils.class);
    private static final String CONTENT = "content";
    private static final String SUM = "sum";
    private static final Integer MAXBLANKROW = 100;
    public Workbook workbook;
    public CellStyle titleStyle;//标题的样式
    public Font titleFont;//标题的字体的样式
    public CellStyle headStyle;//表头样式
    public Font headFont;//表头字体
    public CellStyle contentStyle;//内容样式
    public Font contentFont;//内容字体
    public CellStyle sumStyle;//合计样式
    public Font sumFont;//合计字体

    public void initWorkBook() {
        workbook = new HSSFWorkbook();
    }

    public void initImportBook(FileInputStream fis) throws IOException {
        workbook = new HSSFWorkbook(fis);
    }

    /**
     * 初始化workbook相关属性
     */
    private void init() {
        initWorkBook();
        titleStyle = workbook.createCellStyle();
        titleFont = workbook.createFont();
        headStyle = workbook.createCellStyle();
        headFont = workbook.createFont();
        contentStyle = workbook.createCellStyle();
        contentFont = workbook.createFont();
        sumStyle = workbook.createCellStyle();
        sumFont = workbook.createFont();
        initTitleFont();
        initTitleStyle();
        initHeadFont(headFont);
        initHeadStyle(headStyle, headFont);
        initConentFont();
        initConentSytle();
        initSumFont(sumFont);
        initSumStyle(sumStyle, sumFont);
    }


    /**
     * 获取导入的数据
     *
     * @param fis
     * @return
     * @throws Exception
     */
    public Map<String, Object> getImportDatas(FileInputStream fis) throws Exception {
        try {
            initImportBook(fis);
            int sheetNum = workbook.getNumberOfSheets();
            Map<String, Object> dataMaps = new HashMap<String, Object>();
            for (int i = 0; i < sheetNum; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                String sheetName = sheet.getSheetName();
                String orginSheetName = sheetName;
                if (sheetName.contains("(")) {
                    sheetName = sheetName.substring(0, sheetName.indexOf("(")).trim();
                }
                Row fieldRow = sheet.getRow(0);//获取fields
                int cellNum = fieldRow.getLastCellNum();
                List<String> names = new ArrayList<>();
                for (int cell = 0; cell < cellNum; cell++) {
                    if (fieldRow.getCell(cell) != null && !StringUtils.isNullOrEmpty(fieldRow.getCell(cell).getStringCellValue()))
                        names.add((fieldRow.getCell(cell).getStringCellValue()));
                    else
                        names.add(null);
                }
                int rowNum = sheet.getLastRowNum();
                //去除fieldname和columnname,后期从配置中读取
                List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
                int blankRow = 0;
                for (int row = 2; row <= rowNum; row++) {
                    if (blankRow >= MAXBLANKROW) {
                        break;
                    }
                    Row rowData = sheet.getRow(row);
                    //解决空行问题
                    if (rowData == null) {
                        blankRow++;
                        continue;
                    }
                    // cellNum=rowData.getLastCellNum();
                    cellNum = names.size();
                    Map<String, Object> map = new HashMap<String, Object>();
                    for (int cell = 0; cell < cellNum; cell++) {
                        // if(cell<names.size()&&names.get(cell)!=null &&  rowData.getCell(cell)!=null && org.apache.commons.lang3.StringUtils.isNotBlank(rowData.getCell(cell) + "")){
                        if (cell < names.size() && names.get(cell) != null) {
                            if (rowData.getCell(cell) == null || Strings.isEmpty(rowData.getCell(cell) + "")) {
                                map.put(names.get(cell), null);
                            } else {
                                Cell c = rowData.getCell(cell);
                                if (c.getCellTypeEnum() == CellType.NUMERIC && HSSFDateUtil.isCellDateFormatted(c)) {//对其他类型会抛出异常，所以要特殊处理。
                                    map.put(names.get(cell), c.getDateCellValue());
                                } else if (c.getCellTypeEnum() == CellType.FORMULA) {
                                    map.put(names.get(cell), c.getNumericCellValue());
                                } else {
                                    String value = c.toString();
                                    map.put(names.get(cell), StringUtils.isNullOrEmpty(value) ? null : value);
                                }
                            }

                        }
                    }
                    if (map.size() == 0) {
                        blankRow++;
                        continue;
                    }
                    map.put("_status", "Insert");
                    map.put("__orginSheetName", orginSheetName);
                    map.put("__rowNum", row + 1);
                    datas.add(map);
                }
                dataMaps.put(sheetName, datas);
            }
            return dataMaps;
        } catch (Exception e) {
            logger.info("[ExcelUtils] getImportDatas 异常" + e.getMessage());
            throw e;
        } finally {
            if (fis != null) fis.close();
            if (workbook != null) workbook.close();
        }
    }

    /**
     * 将内容输出到流
     *
     * @param setInfo excel导出的内容
     * @return
     * @throws Exception
     */
    public ByteArrayOutputStream export2Stream(ExcelExportData setInfo)
            throws Exception {
        if (setInfo == null || setInfo.getDataMap() == null) throw new Exception("模版有误，请联系管理员");
        //logger.info("[ExcelUtils] export2Stream setInfo 的参数"+JSON.toJSONString(setInfo));
        init();//初始化一个excel
        LinkedHashMap<String, List<Object>> dataMaps = setInfo.getDataMap();
        Set<String> sheetNames = dataMaps.keySet();
        List<Sheet> sheets = getSheets(sheetNames);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int sheetNum = 0;
        for (Sheet sheet : sheets) {
            int field = createTableFieldRow(setInfo, sheet, sheetNum);//创建field列
            int title = createTableTitleRow(setInfo, sheet, sheetNum, field);//创建标题
            int head = createTableHeadRow(setInfo, sheet, sheetNum, title + field);//创建表头列
            int rowIndex = title + field + head;
            createTableContentRow(setInfo, sheet, sheetNum, rowIndex);//创建内容区域
            //添加合计的列
            createTableSumRow(setInfo, sheet, sheetNum);//创建合计区域
            adjustColumnSize(sheet, setInfo.getFieldNames().get(sheetNum));
            sheetNum++;
        }
        workbook.write(outputStream);
        return outputStream;
    }


    /**
     * 创建标题（需合并单元格）
     *
     * @param setInfo
     * @param Sheet
     * @param sheetNum
     */
    private int createTableTitleRow(ExcelExportData setInfo, Sheet Sheet, int sheetNum, int rowIndex) {
        if (setInfo.getTitles() != null && setInfo.getTitles().length > 0) {
            CellRangeAddress titleRange = new CellRangeAddress(1, 1, 0, setInfo
                    .getFieldNames().get(sheetNum).length);
            Sheet.addMergedRegion(titleRange);
            Row titleRow = Sheet.createRow(rowIndex);//创建标题行
            titleRow.setHeightInPoints(40);//设置title的高度是50个点
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellStyle(titleStyle);
            titleCell.setCellValue(setInfo.getTitles()[sheetNum]);
            return 1;
        }
        return 0;
    }

    /**
     * 获取表格field区域
     *
     * @param setInfo
     * @param Sheet
     * @param sheetNum
     */
    private int createTableFieldRow(ExcelExportData setInfo, Sheet Sheet, int sheetNum) {
        if (setInfo.getFieldNames() != null && !setInfo.getFieldNames().isEmpty() && setInfo.getFieldNames().get(sheetNum) != null && setInfo.getFieldNames().get(sheetNum).length > 0) {
            Row fieldRow = Sheet.createRow(0);
            fieldRow.setHeightInPoints(0);//隐藏改列
            String[] fieldNames = setInfo.getFieldNames().get(sheetNum);
            for (int i = 0; i < fieldNames.length; i++) {
                Cell fieldCell = fieldRow.createCell(i);
                fieldCell.setCellValue(fieldNames[i]);
            }
            return 1;
        }
        return 0;
    }

    /**
     * 判断单元格是否是合并的单元格，暂时不使用
     *
     * @param sheet
     * @param row
     * @param col
     * @return
     */
    @Deprecated
    public boolean isMergedRegion(Sheet sheet, int row, int col) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (col >= firstColumn && col <= lastColumn) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取表格表头区域
     *
     * @param setInfo
     * @param sheet
     * @param sheetNum
     * @param rowIndex
     */
    @SuppressWarnings("unchecked")
    private int createTableHeadRow(ExcelExportData setInfo, Sheet sheet, int sheetNum, int rowIndex) {
        int deep = 0;
        if (setInfo.getColumnNames() != null && !setInfo.getColumnNames().isEmpty() && setInfo.getColumnNames().get(sheetNum) != null && setInfo.getColumnNames().get(sheetNum).length > 0) {
            Row columnRow = sheet.createRow(rowIndex);
            columnRow.setHeightInPoints(20);
            String[] fieldNames = setInfo.getFieldNames().get(sheetNum);
            ExcelField[] excelFields = null;
            if (setInfo.getFields() != null) excelFields = setInfo.getFields().get(sheetNum);
            LinkedHashMap<String, List<Map<String, Object>>> headersMap = setInfo.getHeadersMap();
            if (headersMap != null) {
                deep = 1;
                List<Map<String, Object>> headerMap = headersMap.get(sheet.getSheetName());
                for (int i = 0; i < fieldNames.length; i++) {
                    int num = org.springframework.util.StringUtils.countOccurrencesOf(fieldNames[i], "|") + 1;
                    if (deep < num) deep = num;
                }
                //根据深度创建出所有的行
                Row[] rows = new Row[deep];
                for (int i = 0; i < deep; i++) {
                    rows[i] = sheet.createRow(i + rowIndex);
                }
                if (headerMap == null) {
                    return 0;
                }
                //写入数据并合并列，按列从下往上写入数据，所以只合并了列
                if (deep == 1) {
                    for (int i = 0; i < headerMap.size(); i++) {
                        Cell cell = rows[0].createCell(i);
                        String caption = "";
                        if (null != headerMap.get(i).get("showCaption") && !StringUtils.isNullOrEmpty(headerMap.get(i).get("showCaption").toString())) {
                            caption = headerMap.get(i).get("showCaption").toString();
                        } else {
                            caption = headerMap.get(i).get("caption").toString();
                        }
                        cell.setCellValue(caption);//path加入方便进行行的合并
                        cell.setCellStyle(headStyle);
                    }
                } else {
                    for (int i = 0; i < headerMap.size(); i++) {
                        Map<String, Object> map = headerMap.get(i);//得到第一列的数据
                        int colMergeCount = 0; //列需要合并行的总数
                        int rowNum = 0;//列需要合并的行的起点
                        String rowPath = null;
                        for (int j = rows.length - 1; j >= 0; j--) {
                            String currPath = map.get("path") == null ? "" : map.get("path").toString();
                            rows[j].createCell(i).setCellValue(map.get("showCaption").toString() + "," + currPath);//path加入方便进行行的合并
                            Object parent = map.get("parent");
                            if (parent != null) map = (Map<String, Object>) parent;
                            if (currPath.equals(rowPath)) {
                                colMergeCount++;//如果列中的行需要合并则count++
                                if (colMergeCount == 1) rowNum = j + rowIndex + 1;//记录第一次发现相同列时,加上1就是合并开始列,index 从0开始的
                            }
                            //从倒数第二列开始判断是否需要合并 -----合并的条件是：1、合并的列总数>0(count=>1)，当前路径和前一个路径不一样     2.合并的列总数>0 ，当前路径和前一路径相同，当前行是第一行了
                            if (j < rows.length - 1 && (((!currPath.equals(rowPath) || (j == 0 && currPath == rowPath)) && colMergeCount > 0))) {
                                CellRangeAddress region = new CellRangeAddress(j + rowIndex + 1, rowNum, i, i);//第一次不同时取上次循环的行，和第一次相同的行合并
                                if (j == 0) {
                                    region = new CellRangeAddress(j + rowIndex, rowNum, i, i);//如果循环到顶层时，也相同的path，则当前行和第一次合并的行合并
                                }
                                sheet.addMergedRegion(region);
                                colMergeCount = 0;
                            }
                            rowPath = currPath;
                        }
                    }
                    //行循环和列循环合并的机制一样，只是从左往右合并
                    for (int j = rowIndex; j < rowIndex + deep; j++) {
                        int i = 0;
                        String colPath = null;
                        int rowMergeCount = 0;
                        int colNum = 0;
                        for (Cell cell : sheet.getRow(j)) {
                            String[] cellArray = cell.getStringCellValue().split(",");
                            String currPath = cellArray.length == 1 ? "" : cellArray[1];
                            if (currPath.equals(colPath)) {
                                rowMergeCount++;
                                if (rowMergeCount == 1) colNum = i;
                            }
                            if (i > 0 && (((!currPath.equals(colPath) || (i == headerMap.size() - 1 && currPath.equals(colPath))) && rowMergeCount > 0))) {
                                CellRangeAddress region = new CellRangeAddress(j, j, colNum - 1, i - 1);
                                if (i == headerMap.size() - 1) {
                                    region = new CellRangeAddress(j, j, colNum - 1, i);
                                }
                                sheet.addMergedRegion(region);
                                rowMergeCount = 0;
                            }
                            cell.setCellValue(cellArray[0]);
                            colPath = currPath;
                            cell.setCellStyle(headStyle);
                            i++;
                        }
                    }
                }
                Row headerRowLast = sheet.getRow(rowIndex + deep - 1);
                if (excelFields != null && excelFields.length > 0) {
                    int i = 0;
                    for (Cell cell : headerRowLast) {
                        ExcelField excelField = excelFields[i];
                        if (excelField != null && ((excelField.getIsNull() != null && !excelField.getIsNull()) || (excelField.getIsEnum() != null && excelField.getIsEnum()) || !StringUtils.isNullOrEmpty(excelField.getMappingName()))) {
							/*Font font=workbook.createFont();
							initHeadFont(font);
							font.setColor(IndexedColors.BLACK1.index);
							CellStyle cellStyle=workbook.createCellStyle();
							initHeadStyle(cellStyle,font);
							headCell.setCellValue(columnNames[i]);
						    headCell.setCellStyle(cellStyle);*/
                            //自动列款导致每个备注的长度不同，没有解决这个问题，后续解决
                            String value = "";
                            int width = 2;
                            int height = 3;
                            if (!excelField.getIsNull()) {
                                value = "不能为空";
                                width = 1;
                                height = 2;
                            }
                            if (excelField.getIsEnum()) {
                                value = excelField.getEnumString();
                                width = 5;
                                height = 10;
                            }
                            if (!StringUtils.isNullOrEmpty(excelField.getMappingName())) {
                                if (StringUtils.isNullOrEmpty(value))
                                    value = excelField.getMappingName();
                                else
                                    value = value + "," + excelField.getMappingName();
                                width = 3;
                                height = 2;
                            }

                            createCellComment(sheet, value, cell, width, height);
                            //	cell.setCellStyle(headStyle);
                        }
                        if (excelField != null) {
                            setColumnDefaultStyle(sheet, i, excelField.getCellType());
                        }
                        i++;
                    }

                }

            }
        }
        return deep;
    }

    /**
     * 设置列的样式
     *
     * @param sheet
     * @param index
     * @param cellType
     */
    private void setColumnDefaultStyle(Sheet sheet, int index, CellType cellType) {
        DataFormat format = workbook.createDataFormat();
        CellStyle cellStyle = workbook.createCellStyle();
		/*if(cellType.name().equals(CellType.NUMERIC.name())){
			cellStyle.setDataFormat(format.getFormat("0.00"));
		}else{
			cellStyle.setDataFormat(format.getFormat("@"));
		}*/
        cellStyle.setDataFormat(format.getFormat("@"));
        sheet.setDefaultColumnStyle(index, cellStyle);
    }

    @SuppressWarnings("unchecked")
    private void createTableRow(ExcelExportData setInfo, Sheet sheet, int sheetNum, String type) {
        LinkedHashMap<String, List<Object>> dataMap = null;
        CellStyle cellStyle = null;
        if (CONTENT.equals(type)) {
            dataMap = setInfo.getDataMap();
            cellStyle = contentStyle;
        } else if (SUM.equals(type)) {
            dataMap = setInfo.getSumMap();
            cellStyle = sumStyle;
        }
        if (dataMap != null && !dataMap.isEmpty() && dataMap.get(sheet.getSheetName()) != null && dataMap.get(sheet.getSheetName()).size() > 0) {
            int rowIndex = sheet.getLastRowNum() + 1;
            List<Object> datas = dataMap.get(sheet.getSheetName());
            ExcelField[] excelFields = null;
            if (setInfo.getFields() != null)
                excelFields = setInfo.getFields().get(sheetNum);
            for (Object data : datas) {
                Row row = sheet.createRow(rowIndex);
                String[] fieldNames = setInfo.getFieldNames().get(sheetNum);
                for (int i = 0; i < fieldNames.length; i++) {
                    Cell contentCell = row.createCell(i);
                    ExcelField excelField = null;
                    if (excelFields != null) {
                        excelField = excelFields[i];
                    }
                    if (data instanceof Object[]) {
                        Object[] values = (Object[]) data;
                        if (i < values.length) {
                            if (i == 0 && SUM.equals(type)) contentCell.setCellValue("合计");
                            else contentCell.setCellValue(Toolkit.getFormatDecimalValue(values[i]));
                            contentCell.setCellStyle(cellStyle);
                        }
                    } else if (data instanceof Map) {
                        Map<String, Object> valueMap = (Map<String, Object>) data;
                        Object value = valueMap.get(fieldNames[i]);
                        if (excelField != null) {

                            String enumString = excelField.getEnumString();
                            if (excelField.getIsEnum() != null && excelField.getIsEnum() && enumString != null && !"".equals(enumString.trim()) && value != null) {
                                Map<String, Object> enumMap = JSON.parseObject(enumString, Map.class);
                                value = enumMap.get(value.toString()) == null ? value : enumMap.get(value.toString());
                            }
                        }
                        if (i == 0 && SUM.equals(type)) contentCell.setCellValue("合计");
                        else contentCell.setCellValue(Toolkit.getFormatDecimalValue(value));
                        contentCell.setCellStyle(cellStyle);
                    }
                }
                rowIndex++;
            }

        }


    }

    /**
     * 获取表格表体区域
     *
     * @param setInfo
     * @param Sheet
     * @param sheetNum
     */
    private void createTableContentRow(ExcelExportData setInfo, Sheet Sheet, int sheetNum, int rowIndex) {
        createTableRow(setInfo, Sheet, sheetNum, CONTENT);
    }

    /**
     * 创建合计列
     *
     * @param setInfo
     * @param Sheet
     * @param sheetNum
     */
    private void createTableSumRow(ExcelExportData setInfo, Sheet Sheet, int sheetNum) {
        createTableRow(setInfo, Sheet, sheetNum, SUM);
    }

    /**
     * 自动列宽
     *
     * @param sheet
     * @param fieldNames
     */
    private void adjustColumnSize(Sheet sheet, String[] fieldNames) {
        if (sheet instanceof SXSSFSheet) {
            SXSSFSheet currSheet = (SXSSFSheet) sheet;
            currSheet.trackAllColumnsForAutoSizing();
            for (int i = 0; i < fieldNames.length; i++) {
                currSheet.autoSizeColumn(i);
            }
        } else {
            for (int i = 0; i < fieldNames.length; i++) {
                sheet.autoSizeColumn(i, true);
            }
        }

    }

    /**
     * 获取sheet的名称
     *
     * @param sheetNames
     * @return
     */
    private List<Sheet> getSheets(Set<String> sheetNames) {
        List<Sheet> sheets = new ArrayList<Sheet>();
        for (String sheetName : sheetNames) {
            sheets.add(workbook.createSheet(sheetName));
        }
        return sheets;
    }

    /**
     * @Description:初始化标题样式
     */
    private void initTitleStyle() {
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        titleStyle.setFont(titleFont);//设置标题字体样式
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        titleStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.index);//设置标题的背景色是天蓝色
        setBorder(titleStyle);

    }

    /**
     * @Description: 初始化标题行字体
     */
    private void initTitleFont() {
        titleFont.setFontName("华文楷体");
        titleFont.setFontHeightInPoints((short) 15);
        titleFont.setBold(true);
        titleFont.setCharSet(Font.DEFAULT_CHARSET);
        titleFont.setColor(IndexedColors.BLACK.index);
    }

    /**
     * 初始化表头样式
     */
    private void initHeadStyle(CellStyle cellStyle, Font font) {
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);//设置标题的背景色是灰色
        cellStyle.setFont(font);
        setBorder(cellStyle);
    }

    private void setBorder(CellStyle cellStyle) {
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.index);
    }

    /**
     * 初始化表头字体
     */
    private void initHeadFont(Font font) {
        font.setColor(IndexedColors.BLACK.index);
        font.setFontHeightInPoints((short) 13);
        //font.setFontName("华文黑体");
        //font.setBold(true);导出时使用了备注导致第一列的数据颜色变化，目前不知道原因

    }

    /**
     * 初始化表头字体
     */
    private void initSumFont(Font font) {
        font.setColor(IndexedColors.RED.index);
        font.setFontHeightInPoints((short) 13);
        font.setBold(true);
        //font.setFontName("华文黑体");
        //font.setBold(true);导出时使用了备注导致第一列的数据颜色变化，目前不知道原因

    }

    /**
     * 初始化表头样式
     */
    private void initSumStyle(CellStyle cellStyle, Font font) {
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.index);//设置标题的背景色是灰色
        cellStyle.setFont(font);
        setBorder(cellStyle);
    }

    /**
     * 初始化表体样式
     */
    private void initConentSytle() {
        contentStyle.setAlignment(HorizontalAlignment.LEFT);
        contentStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentStyle.setFont(contentFont);
        setBorder(contentStyle);
    }

    /**
     * å
     * 初始化表体样式
     */
    private void initConentFont() {
        contentFont.setFontHeightInPoints((short) 10);
    }

    /**
     * 设置批注
     *
     * @param sheet
     * @param value
     */
    private void createCellComment(Sheet sheet, String value, Cell cell, int width, int height) {
        if (!StringUtils.isNullOrEmpty(value)) {
            Drawing<?> patr = sheet.createDrawingPatriarch();
            //dx1 第1个单元格中x轴的偏移量,dy1 第1个单元格中y轴的偏移量,dx2 第2个单元格中x轴的偏移量,dy2 第2个单元格中y轴的偏移量,col1 第1个单元格的列号,row1 第1个单元格的行号,col2 第2个单元格的列号,row2 第2个单元格的行号
            ClientAnchor anchor = patr.createAnchor(0, 0, 0, 0, cell.getColumnIndex(), cell.getRowIndex(), cell.getColumnIndex() + width, cell.getRowIndex() + height);//创建批注位置
            Comment comment = patr.createCellComment(anchor);
            Font font = workbook.createFont();
            font.setBold(true);
            font.setColor(IndexedColors.RED.getIndex());
            if (sheet instanceof SXSSFSheet) {
                XSSFRichTextString xssfRichTextString = new XSSFRichTextString(value);
                xssfRichTextString.applyFont(font);
                comment.setString(xssfRichTextString);
            } else {
                HSSFRichTextString hssfRichTextString = new HSSFRichTextString(value);
                hssfRichTextString.applyFont(font);
                comment.setString(hssfRichTextString);
            }
            comment.setVisible(false);//设置批注默认不显示
            cell.setCellComment(comment);
        }
    }


    public static void main(String[] args) throws IOException {
        File dir = new File("/Users/limy/Desktop/requestCollects");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, "demo.xlsx");
        if (!file.exists()) {
            file.createNewFile();
        }

    }
}