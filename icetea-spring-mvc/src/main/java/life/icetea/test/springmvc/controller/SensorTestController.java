package life.icetea.test.springmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * @author icetea
 */
@RestController
@RequestMapping("sensor")
public class SensorTestController {

    @RequestMapping("test")
    public Map<String, Object> test(@RequestParam Map<String, Object> map) throws IOException {
        System.out.println(map);

        String dataList = (String) map.get("data_list");

        // Base64 解密
        byte[] decoded = Base64.getDecoder().decode(dataList);

        GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(decoded));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffers = new byte[1024];
        int offset = -1;
        while ((offset = gzipInputStream.read(buffers)) != -1) {
            byteArrayOutputStream.write(buffers, 0, offset);
        }
        byte[] bytes = byteArrayOutputStream.toByteArray();
        String s = new String(bytes, "utf-8");


        System.out.println(s);
        return map;
    }

}
