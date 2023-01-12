package life.icetea.test.springmvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author icetea
 */
@RequestMapping("test")
@RestController
@Slf4j
public class TestResponseEntityController {


    /**
     * 使用#{@link ResponseEntity}做下载接口
     */
    @RequestMapping(value = "/download")
    public ResponseEntity download() {
        try {
            ClassPathResource resource = new ClassPathResource("download.jpg");
            InputStream inputStream = resource.getInputStream();
            HttpHeaders httpHeaders = new HttpHeaders();
            // 内容类型
//            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM); // 二进制文件流(常见的文件都可以使用)
            httpHeaders.setContentType(MediaType.IMAGE_JPEG); // 图片
//            httpHeaders.setContentType(MediaType.APPLICATION_JSON); // json
//            httpHeaders.setContentType(MediaType.APPLICATION_PDF); // pdf

            String downloadFileName = URLEncoder.encode("download.jpg", StandardCharsets.UTF_8.toString());

            /**
             * https://www.jianshu.com/p/d4a85d025768/
             * ContentDisposition 响应头可以设置浏览器处理文件行为,一般有两种方式
             * 1. attachment; filename="download.jpg" 以附件形式下载， filename若有中文需要使用URL进行编码
             * 2. inline; filename="download.jpg"直接在页面显示
             */
            // 设置浏览器弹窗下载
//            ContentDisposition attachment = ContentDisposition.builder("attachment").filename(downloadFileName).build();
            ContentDisposition attachment = ContentDisposition.builder("inline").filename(downloadFileName).build();
            // 设置
            httpHeaders.setContentDisposition(attachment);

            ResponseEntity responseEntity = new ResponseEntity(FileCopyUtils.copyToByteArray(inputStream), httpHeaders, HttpStatus.OK);
            return responseEntity;
        } catch (IOException e) {
            log.error("下载报错", e);
        }
        return null;
    }

}
