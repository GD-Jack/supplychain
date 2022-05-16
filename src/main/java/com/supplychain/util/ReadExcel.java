package com.supplychain.util;

import com.supplychain.entity.Commodity;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ReadExcel {
    //总行数
    private int totalRows = 0;
    //总条数
    private int totalCells = 0;
    //错误信息接收器
    private String errorMsg;
    //构造方法
    public ReadExcel(){}
    //获取总行数
    public int getTotalRows()  { return totalRows;}
    //获取总列数
    public int getTotalCells() {  return totalCells;}
    //获取错误信息
    public String getErrorInfo() { return errorMsg; }
    /**
     * 读EXCEL文件，获取信息集合
     * @param
     * @return
     */
    public List<Commodity> getExcelInfo(MultipartFile mFile) {
        String fileName = mFile.getOriginalFilename();//获取文件名
        List<Commodity> commodity = null;
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            commodity = createExcel(mFile.getInputStream(), isExcel2003);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commodity;
    }
    /**
     * 根据excel里面的内容读取客户信息
     * @param is 输入流
     * @param isExcel2003 excel是2003还是2007版本
     * @return
     * @throws IOException
     */
    public List<Commodity> createExcel(InputStream is, boolean isExcel2003) {
        List<Commodity> commodity = null;
        try{
            Workbook wb = null;
            if (isExcel2003) {// 当excel是2003时,//
                // 创建excel2003 HSSFWorkbook:是操作Excel2003以前（包括2003）的版本，扩展名是.xls
                wb = new HSSFWorkbook(is);
            } else {// 当excel是2007时,创建excel2007 //
                //XSSFWorkbook:是操作Excel2007的版本，扩展名是.xlsx
                wb = new XSSFWorkbook(is);
            }
            commodity = readExcelValue(wb);// 读取Excel里面客户的信息
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commodity;
    }
    /**
     * 读取Excel里面客户的信息
     * @param wb
     * @return
     */
    private List<Commodity> readExcelValue(Workbook wb) {
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<Commodity> commodityList = new ArrayList<Commodity>();
        // 循环Excel行数
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            boolean b = isRowEmpty(row);
            if (b==true){
                continue;
            }
            if (null==row || row.toString().isEmpty() ){
                continue;
            }

            Commodity commodity = new Commodity();
            // 循环Excel的列（这里根据表字段不同，自行更换）
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
                    continue;
                }
                if (null != cell) {
                    if (c == 0) {
                        DecimalFormat decimalFormat = new DecimalFormat("0");
                        String format = decimalFormat.format(cell.getNumericCellValue());
                        commodity.setCommodityId(Integer.valueOf(format));
                    } else if (c == 1) {
                        String s = cell.getStringCellValue();
                        commodity.setCommodityName(s);
                    } else if (c == 2) {
                        String s = cell.getStringCellValue();
                        commodity.setCommodityCode(s);
                    } else if (c == 3) {
                        DecimalFormat decimalFormat = new DecimalFormat("0");
                        String format = decimalFormat.format(cell.getNumericCellValue());
                        commodity.setSalesVolumes(Integer.valueOf(format));
                    } else if (c == 4) {
                        DecimalFormat decimalFormat = new DecimalFormat("0.00");
                        String format = decimalFormat.format(cell.getNumericCellValue());
                        commodity.setPrice(Float.valueOf(format));
                    } else if (c == 5) {
                        DecimalFormat decimalFormat = new DecimalFormat("0.00");
                        String format = decimalFormat.format(cell.getNumericCellValue());
                        commodity.setCost(Float.valueOf(format));
                    } else if (c == 6) {
                        String s = cell.getStringCellValue();
                        commodity.setUnit(s);
                    } else if (c == 7) {
                        String s = new Date(cell.getDateCellValue().getTime()).toString();
                        commodity.setDate(s);
                    } else if (c == 8) {
                        String s = cell.getStringCellValue();
                        commodity.setStoreName(s);
                    }
                }
            }
            commodityList.add(commodity);
        }
        return commodityList;
    }

    /**
     * 验证EXCEL文件
     *
     * @param filePath
     * @return
     */
    public boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            errorMsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }
    /*
    * 处理空白行
    *
    * */
    public boolean isRowEmpty(Row row){
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
                return false;
        }
        return true;
    }

    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }
    //@描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}
