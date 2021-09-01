package com.chenning.springbootlearn.excel.EasyExcel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author nchen
 * @Date 2021/9/1 9:18
 * @Version 1.0
 * @Description
 */
@RestController
@RequestMapping(value ="excel")
public class Controller {

    /**
     excel文件的下载(单个sheet)
     */
    @GetMapping("download1")
    public void download1(HttpServletResponse httpServletResponse, HttpServletRequest request) throws IOException {
        AnalysisExcelByEasyExcel excel=new AnalysisExcelByEasyExcel();
        excel.test5(httpServletResponse,request);
    }

    /**
     excel文件的下载(多个sheet)
     */
    @GetMapping("download2")
    public void download2(HttpServletResponse httpServletResponse, HttpServletRequest request) throws IOException {
        AnalysisExcelByEasyExcel excel=new AnalysisExcelByEasyExcel();
        excel.test6(httpServletResponse,request);
    }


    ///**
    // excel文件的上传
    // */
    //@PostMapping("upload")
    //@ResponseBody
    //public String upload(MultipartFile file) throws IOException {
    //    EasyExcel.read(file.getInputStream(), DemoData.class, new DemoDataListener()).sheet().doRead();
    //    return "success";
    //}

}
