package com.chenning.springbootlearn.util.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author nchen
 * @version 1.0
 * @date 2021/3/10 14:36
 */
public class ExcelUtils {


    /**
     * 文件路径转Workbook
     * @param filePath 文件完全路径
     * @return
     */
    public static Workbook readExcel(String filePath) {
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if (".xls".equals(extString)) {
                return wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                return wb = new XSSFWorkbook(is);
            } else {
                return wb = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return wb;
    }







    /**
     * 流转为Workbook
     * @param inputStream   文件IO
     * @param extString    文件名后缀
     * @return
     */
    public static Workbook readExcel(InputStream inputStream, String extString) {
        Workbook wb = null;
        try {
            if (".xls".equals(extString)) {
                return wb = new HSSFWorkbook(inputStream);
            } else if (".xlsx".equals(extString)) {
                return wb = new XSSFWorkbook(inputStream);
            } else {
                return wb = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return wb;
    }


    public static Object getCellFormatValue(Cell cell) {
        Object cellValue = null;
        if (cell != null) {
            //判断cell类型
            switch (cell.getCellType()) {
                case NUMERIC: {
                    //日期格式化
                    short format = cell.getCellStyle().getDataFormat();
                    if (format == 14 || format == 31 || format == 57 || format == 58 || format == 176) {
                        Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                        return date;
                    }
                    if (format == 49) {//电话号码格式化
                        DataFormatter df = new DataFormatter();
                        df.addFormat("###########", null);
                        cellValue = df.formatCellValue(cell);
                        return cellValue;
                    }
                    //数字转换成文本格式
                    double numericCellValue = cell.getNumericCellValue();
                    if (String.valueOf(numericCellValue).endsWith(".0")) {
                        DecimalFormat df = new DecimalFormat("#");
                        cellValue = df.format(cell.getNumericCellValue());
                    } else {
                        //如果本来是小数
                        cellValue = String.valueOf(cell.getNumericCellValue()).trim();
                    }
                    break;
                }
                case FORMULA: {
                    //判断cell是否为日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    } else {
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                case BOOLEAN:{
                    cellValue =String.valueOf(cell.getBooleanCellValue());
                }
                default:
                    cellValue = "";
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }


    public static boolean isFilexlsx(String fileName) {
        boolean flag = false;
        if (!StringUtils.isEmpty(fileName)) {
            String suffix = getSuffixFilename(fileName);
            if (".xlsx".equalsIgnoreCase(suffix)) {
                flag = true;
            } else if (".xls".equalsIgnoreCase(suffix)) {
                flag = true;
            }
        }
        return flag;
    }


    /**
     * 获取文件名前缀   日期拼接
     *
     * @param fileName
     * @return
     */
    public static String getPrefixFilename(String fileName) {
        return fileName.replaceAll("[.][^.]+$", "") + new SimpleDateFormat("yyyyMMdd").format(new Date());
    }


    /**
     * 获取文件后缀
     *
     * @param fileName
     * @return
     */
    public static String getSuffixFilename(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }


    /**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }


    /**
     * @param srcInputStream 源文件stream
     * @param srcFileName    源文件名称
     * @param path           设置临时文件path
     * @return 临时file对象（JVM回收时删除）
     * @throws IOException
     */
    public static File copyInputStreamToFile(InputStream srcInputStream, String srcFileName, String path)
            throws IOException {
        File fl = new File(path);
        if (!fl.exists()) {
            fl.mkdirs();
        }
        File file = File.createTempFile(getPrefixFilename(srcFileName), getSuffixFilename(srcFileName), fl);
        file.deleteOnExit();

        FileOutputStream outputStream = new FileOutputStream(file);
        try {
            int read;
            byte[] bytes = new byte[1024];

            while ((read = srcInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
                outputStream.flush();
            }
        } catch (Exception e) {
            System.out.println("执行 outStream 发生了异常：" + e.getMessage());
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
            }
            try {
                srcInputStream.close();
            } catch (IOException e) {
            }
        }
        return file;
    }


    /**
     * @param srcFile 源file文件
     * @return InputStream
     * @throws FileNotFoundException
     */
    public static InputStream FileToInputStream(File srcFile) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(srcFile);
        return inputStream;
    }



    public  static  InputStream WorkbookToInputStream(Workbook workbook){
        InputStream is=null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            workbook.write(bos);
            byte[] barray = bos.toByteArray();
            is = new ByteArrayInputStream(barray);
        } catch (IOException e) {
            e.printStackTrace();
        }
         return  is;
    }
}
