package com.chenning.common.util.commonsIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;


/**
 * @Author nchen
 * @Date 2021/9/1 11:50
 * @Version 1.0
 * @Description Windos和 Linux 文件路径
 * （1）windows的文件路径格式“E:\Python\workplace\codes”单反斜杠的方式，
 * 但是在很多编程语言中会不认识“\”字符，可能会把它识别成转译字符，
 * 通常我们在windows引用路径的时候需要用“\\”来表示“\”，
 * windows的文件路径的正确写法在程序中最好是“E:\\Python\\workplace\\codes”。
 * <p>
 * <p>
 * <p>
 * （2）linux的文件路径格式为“python/workplace/codes”这种单斜杠的方式，
 * 这种路径很多时候是可以适用在windows下的，但是会有一定的弊端，
 * 当路径中有空格的时候这种路径方式就不适用与windows了
 *
 *
 * 推荐使用 File.separator 在 LUNIX 系统上，此字段的值为 '/'；在 Microsoft Windows 系统上，它为 '\'(java获取 File.separator=="\\")。
 *
 */
public class FileUtil {

    /**
     * @param path 【文件名称或者文件全路径】 E:\迅雷下载\最新文档.xlsx||最新文档.xlsx
     * @return 获取文件后缀  =>  xlsx
     */
    public static String getExtension(String path) {
        return FilenameUtils.getExtension(path);
    }


    /**
     * @param path 【文件名称或者文件全路径】  E:\迅雷下载\最新文档.xlsx||最新文档.xlsx
     * @return 获取文件名  =>  最新文档
     */
    public static String getBaseName(String path) {
        return FilenameUtils.getBaseName(path);
    }

    /**
     * @param path 【文件名称或者文件全路径】  E:\迅雷下载\最新文档.xlsx||最新文档.xlsx
     * @return 获取文件全名  =>  最新文档.xlsx
     */
    public static String getName(String path) {
        return FilenameUtils.getName(path);
    }


    /**
     * @param bathPath 目录 (会自动去除或添加不符合要求的 / \ )
     * @param fileName 文件名称
     * @return
     */
    public static String concat(String bathPath, String fileName) {
        //FileUtil.concat("D\\\\\\//a/b\\\\///c","ww.xlsx");=> D\a\b\c\ww.xlsx
        return FilenameUtils.concat(bathPath, fileName);
    }

    /**
     * 判断文件扩展名
     *
     * @param filename  文件全名或者完全路径下的文件全名
     * @param extension xlsx
     */
    public static Boolean isExtension(String filename, String extension) {
        return FilenameUtils.isExtension(filename, extension);
    }

    /**
     * 判断文件扩展名(后缀)是否是集合某一个
     *
     * @param filename   文件全名或者完全路径下的文件全名
     * @param extensions 类型集合  xlsx,xls
     */
    public static Boolean isExtensions(String filename, List<String> extensions) {
        //FileUtil.isExtensions("E:\\迅雷下载\\IO流.xls", Arrays.asList("xlsx","xlss");
        return FilenameUtils.isExtension(filename, extensions);
    }


    /**
     * 创建文件夹，如果他的父目录不存也会创建
     *
     * @param directory 文件夹路径  D:\OwnFile\One
     * @throws IOException
     */

    public static void forceMkdir(String directory) {
        try {
            FileUtils.forceMkdir(new File(directory));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 判断目录是否存在
     * @param directory
     * @return
     */
    public static boolean existsDirectory(String directory) {
        File file = new File(directory);
        return file.exists() && file.isDirectory();
    }


    /**
     * 创建文件
     * 检测文件夹和文件是否存在，不存在则创建
     *
     * @param directory 文件路径
     * @param fileName  文件名
     * @return 文件
     */
    public static File createFile(String directory, String fileName) {
        String filePath = "";
        if (Objects.isNull(directory) || directory.isEmpty()) {
            filePath = fileName;
        } else {
            filePath = FileUtil.concat(directory, fileName);
            File dir = new File(directory);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }


    /**
     * 将文件A的内容复制到文件B
     *
     * @param fromPath 源文件地址(路径必须存在此文件)
     * @param toPath   目标文件地址(不存在路径将会自动创建(包括父目录))
     * @throws IOException
     */
    public static void copyToFile(String fromPath, String toPath) throws IOException {
        InputStream inputStream;
        File file = new File(toPath);
        inputStream = new FileInputStream(fromPath);//用字节流的方式cope文件
        FileUtils.copyToFile(inputStream, file);//里面会自动关闭inputStream
    }


}
