package com.chenning.springbootlearn.mybatisPlus.Io;

import com.chenning.springbootlearn.util.commonsIO.FileUtil;
import com.google.common.collect.Lists;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

/**
 * @Author nchen
 * @Date 2021/9/1 11:57
 * @Version 1.0
 * @Description
 */
public class FileUtilTest {

    @Test
    public void test1() throws IOException {
        String path1="E:\\迅雷下载\\最新文档.xlsx";
        String path2="IO流.jpg";
        //String extension = FilenameUtils.getExtension("E:\\迅雷下载\\IO流.jpg");
        //String extension2 = FilenameUtils.getExtension("IO流.jpg");
        //System.out.println(extension);
        //System.out.println(extension2);
        //System.out.println(FileUtil.getName(path2));
        //System.out.println(FileUtil.concat("D\\\\\\//a/b\\\\///c","ww.xls"));
        //System.out.println(File.separator+File.separator);
        //System.out.println(FileUtil.isExtensions("E:\\迅雷下载\\IO流.xls", Arrays.asList("xlsx","xlss")));
        //FileUtil.forceMkdir("D:\\OwnFile\\One");
        //FileUtil.copyToFile("D:/OwnFile/One/a.txt","D:/OwnFile/Two/b.txt");
        //FileUtil.createFile("d:/TTT/One\\//\\","a.txt");
        //System.out.println(FileUtil.existsDirectory("d:"+File.separator+"TTT" +File.separator+"One"));


        Set<String> strings=null;
        ArrayList<String> strings1 = Lists.newArrayList(strings);
        System.out.println(strings1);
    }
}
