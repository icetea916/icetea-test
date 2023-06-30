package life.icetea.test.itext;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.*;

import java.nio.charset.StandardCharsets;


public class CertificateGenerator {
    public static void main(String[] args) throws Exception {
        // 读取证书模板文件
        PdfDocument pdfDoc = new PdfDocument(new PdfReader("template.pdf"), new PdfWriter("cert.pdf"));

        // 构建字体对象，默认宋体
        com.itextpdf.kernel.font.PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);

        // 获取第一页
        PdfPage page = pdfDoc.getPage(1);

        // 构建文本替换器
        TextReplacer replacer = new TextReplacer();

        PdfDictionary dict = page.getPdfObject().getAsDictionary(PdfName.Contents);
        PdfArray objects = dict.getAsArray(PdfName.Contents);
        for (PdfObject obj : objects) {
            if (obj instanceof PdfStream) {
                PdfStream stream = (PdfStream) obj;
                byte[] data = stream.getBytes();
                stream.setData(new String(data).replace("{{username}}", "冰红茶").getBytes(StandardCharsets.UTF_8));
            }
        }

        // 关闭文档
        pdfDoc.close();

    }

}
