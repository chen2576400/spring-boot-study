package com.chenning.springbootlearn.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author nchen
 * @Date 2021/9/1 10:47
 * @Version 1.0
 * @Description
 */
public class FileDemon {

    public void demon1() throws IOException {
        //**************[new fileoutputstream(文件路径)是否会自动创建文件]
// TODO:  1：当myfile.txt所在目录已经存在时1可以创建文件。
        FileOutputStream fos = new FileOutputStream("D:/111/222/myfile.txt");
// TODO: 2 当myfile.txt所在目录不存在时： 不能创建文件。需要先创建出目录
        {
            //创建目录
            // File outDir =new File("D:/111/222");
            //outDir.mkdirs();
        }

        //=================创建文件的两种方式START========================
        { //方式1

            File file = new  File("d:/a.txt");
            File file1 = new  File("E:\\OwnFile","a.txt");

            file.createNewFile();//必须要执行才会生成文件
            file1.createNewFile();
        }
        {//方式2
           File file = new File("d:/a.txt");
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(123);
            outputStream.close();
            outputStream.flush();//必须刷新文件才有内容
        }
        //=================创建文件的两种方式END========================




        //=================创建临时文件START=============================
        {
            //方法默认的保存路径为：C:\Documents and Settings\Administrator\Local Settings\Temp 。
            java.io.File.createTempFile("前缀名称","后缀xlsx");//
        }
        {
            //在指定目录中创建一个新的空文件 (如果路径不存在，则创建失败。)
            File file = new File("D:\\DemonFile\\tmp\\");
            if (!file.exists())  file.mkdirs();
            File.createTempFile("","",file);
        }
        //=================创建临时文件END=============================
    }







}
