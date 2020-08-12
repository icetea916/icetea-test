package icetea.test.poi;

import org.apache.poi.xwpf.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PoiTest {

    /**
     * 根据指定的参数值、模板，生成 word 文档
     *
     * @param param        需要替换的变量
     * @param templatePath 模板地址
     */
    public static XWPFDocument generateWord(Map<String, Object> param, String templatePath) throws IOException {
        XWPFDocument doc = new XWPFDocument(new FileInputStream(templatePath));
        if (param != null && param.size() > 0) {
            // 处理段落
            List<XWPFParagraph> paragraphList = doc.getParagraphs();
//            processParagraphs(paragraphList, param, doc);
            // 处理表格
            Iterator<XWPFTable> it = doc.getTablesIterator();
            while (it.hasNext()) {
                XWPFTable table = it.next();
                List<XWPFTableRow> rows = table.getRows();
                for (XWPFTableRow row : rows) {
                    List<XWPFTableCell> cells = row.getTableCells();
                    for (XWPFTableCell cell : cells) {
                        List<XWPFParagraph> paragraphListTable = cell.getParagraphs();
//                        processParagraphs(paragraphListTable, param, doc);
                    }
                }
            }
        }
        return doc;
    }

}
