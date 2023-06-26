package com.liuwei.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.liuwei.filter.FreezeAndFilter;
import com.liuwei.listener.UserListener;
import com.liuwei.vo.AddUserVo;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

public class ExcelUtils {
    public static HorizontalCellStyleStrategy horizontalCellStyleStrategy() {
        //1、头
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //设置背景颜色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        //设置头字体
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 13);
        headWriteFont.setBold(true);
        headWriteCellStyle.setWriteFont(headWriteFont);
        //设置头居中
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);

        //2、内容策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //设置 水平居中
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //设置边框样式
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);//细实线
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);

        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }
    //下载
    public static<T> void downLoad(HttpServletResponse response, Class<T> beanVo, List<T> beanList) throws IOException{
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("用户数据表单", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), beanVo).sheet("用户数据").
                registerWriteHandler(new FreezeAndFilter()).registerWriteHandler(ExcelUtils.horizontalCellStyleStrategy())
                .doWrite(beanList);
    }

    //上传
    public static<T, L extends AnalysisEventListener<T>> void upload(MultipartFile file, Class<T> BeanVo, L listener) throws IOException {
        EasyExcel.read(file.getInputStream(), BeanVo, listener).sheet().doRead();
    }

}
