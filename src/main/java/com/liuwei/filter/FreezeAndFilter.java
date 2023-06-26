package com.liuwei.filter;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

public class FreezeAndFilter implements SheetWriteHandler {
    public int colSplit = 0, rowSplit = 1, leftmostColumn = 0, topRow = 1;
    //public String autoFilterRange = "1:1";
    //设定筛选的表头范围
    public String autoFilterRange = "A:D";
    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Sheet sheet = writeSheetHolder.getSheet();
        //固定表头
        sheet.createFreezePane(colSplit, rowSplit, leftmostColumn, topRow);
        //设定筛选行
        sheet.setAutoFilter(CellRangeAddress.valueOf(autoFilterRange));
    }
}
