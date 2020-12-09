import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormatRegistry;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;
import lombok.SneakyThrows;

import java.io.FileOutputStream;
import java.io.InputStream;

public class MyTask implements Runnable {

    @SneakyThrows
    @Override
    public void run() {
        InputStream in = TestOpenOffice.class.getClassLoader().getResourceAsStream("test-open-office.docx");
        FileOutputStream out = new FileOutputStream("test-open-office-pdf.pdf");

        OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);
        connection.connect();
        DocumentFormatRegistry formatRegistry = new DefaultDocumentFormatRegistry();
        DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);


        converter.convert(in, formatRegistry.getFormatByFileExtension("doc"), out, formatRegistry.getFormatByFileExtension("pdf"));
    }
}
