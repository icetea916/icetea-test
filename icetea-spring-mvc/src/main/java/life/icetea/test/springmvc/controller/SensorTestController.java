package life.icetea.test.springmvc.controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.bulk.CreateOperation;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

/**
 * @author icetea
 */
@RestController
@Slf4j
@RequestMapping("sa")
@CrossOrigin(origins = "*")
public class SensorTestController {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @RequestMapping("icetea")
    public ResponseEntity test(@RequestParam Map<String, Object> paramsMap, HttpServletRequest request) throws IOException {
        JSONObject params = new JSONObject(paramsMap);
        log.info("接收到的参数：{}", params);

        JSONArray dataList = new JSONArray();

        if (StringUtils.hasLength(params.getString("data"))) {
//            String urlDecode = URLDecoder.decode(params.getString("data"), "utf-8");
            byte[] de64Bytes = base64Decode(params.getString("data"));
            if (StringUtils.hasLength(params.getString("gzip")) && "1".equals(params.getString("gzip"))) {
                // gzip解压缩
                dataList.add(JSON.parseObject(gunzip(de64Bytes)));
            } else {
                // 不需要解压
                dataList.add(JSON.parseObject(new String(de64Bytes, StandardCharsets.UTF_8)));
            }
        }

        if (StringUtils.hasLength(params.getString("data_list"))) {
//            String urlDecode = URLDecoder.decode(params.getString("data_list"), "utf-8");
            byte[] de64Bytes = base64Decode(params.getString("data_list"));
            if (StringUtils.hasLength(params.getString("gzip")) && "1".equals(params.getString("gzip"))) {
                // gzip解压缩
                JSONArray jsonArray = JSON.parseArray(gunzip(de64Bytes));
                dataList.addAll(jsonArray);
            } else {
                // 不需要解压
                dataList.addAll(JSON.parseArray(new String(de64Bytes, StandardCharsets.UTF_8)));
            }
        }

        // 解析到的数据
        log.info("解析到的数据：{}", dataList);

        List<BulkOperation> collect = dataList.stream().map(d -> {
            CreateOperation<Object> createOperation = new CreateOperation.Builder<>().document(d).build();
            BulkOperation bulkOperation = new BulkOperation.Builder().create(createOperation).build();
            return bulkOperation;
        }).collect(Collectors.toList());

        // 保存数据至elasticSearch
        BulkResponse bulk = elasticsearchClient.bulk(
                new BulkRequest.Builder()
                        .index("test-sa")
                        .operations(collect)
                        .build()
        );

        if (bulk.errors()) {
            bulk.items().stream().filter(r -> r.status() != 200).forEach(
                    r -> log.warn("bulk中item执行报错", r.error())
            );
        } else {
            log.info("执行成功：耗时{}ms", bulk.took());
            bulk.items().stream().forEach(r -> log.info("item:{}", r));
        }

        return ResponseEntity.ok().build();
    }

    private static byte[] base64Decode(String src) {
        // Base64 解密
        byte[] decoded = Base64.getDecoder().decode(src);
        return decoded;
    }

    private static String gunzip(byte[] srcBytes) {
        try (GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(srcBytes))) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffers = new byte[1024];
            int offset = -1;
            while ((offset = gzipInputStream.read(buffers)) != -1) {
                byteArrayOutputStream.write(buffers, 0, offset);
            }
            String desStr = new String(byteArrayOutputStream.toByteArray(), "utf-8");
            return desStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
//        String src = "eyJkaXN0aW5jdF9pZCI6IjE3ZmI5YjA0NTk5NTg1LTBiNjU5YTZiNTNhYWIwOC00NTU1NjYtMjAwNzA0MC0xN2ZiOWIwNDU5YTE1YiIsImxpYiI6eyIkbGliIjoianMiLCIkbGliX21ldGhvZCI6ImNvZGUiLCIkbGliX3ZlcnNpb24iOiIxLjEzLjEwIn0sInByb3BlcnRpZXMiOnsiJHNjcmVlbl9oZWlnaHQiOjExMjAsIiRzY3JlZW5fd2lkdGgiOjE3OTIsIiRsaWIiOiJqcyIsIiRsaWJfdmVyc2lvbiI6IjEuMTMuMTAiLCIkbGF0ZXN0X3JlZmVycmVyIjoidXJs55qEZG9tYWlu6Kej5p6Q5aSx6LSlIiwiJGxhdGVzdF9yZWZlcnJlcl9ob3N0IjoidXJs55qEZG9tYWlu6Kej5p6Q5aSx6LSlIiwiJGxhdGVzdF90cmFmZmljX3NvdXJjZV90eXBlIjoidXJs55qEZG9tYWlu6Kej5p6Q5aSx6LSlIiwiJGxhdGVzdF9zZWFyY2hfa2V5d29yZCI6InVybOeahGRvbWFpbuino+aekOWksei0pSIsIiRlbGVtZW50X3R5cGUiOiJhIiwiJGVsZW1lbnRfY2xhc3NfbmFtZSI6IiIsIiRlbGVtZW50X3RhcmdldF91cmwiOiIjL2JhciIsIiRlbGVtZW50X2NvbnRlbnQiOiJHbyB0byBCYXIiLCIkdXJsIjoiZmlsZTovLy9Vc2Vycy9pY2V0ZWEvbXlwcm9qZWN0L3NlbnNvcl90ZXN0L3NhLXNkay1qYXZhc2NyaXB0LTEuMjEuMTIvZGVtby92dWUvaW5kZXguaHRtbCMvZm9vIiwiJHVybF9wYXRoIjoiL1VzZXJzL2ljZXRlYS9teXByb2plY3Qvc2Vuc29yX3Rlc3Qvc2Etc2RrLWphdmFzY3JpcHQtMS4yMS4xMi9kZW1vL3Z1ZS9pbmRleC5odG1sIiwiJHRpdGxlIjoiVnVlICYgU2Vuc29ycyBTREsiLCIkdmlld3BvcnRfd2lkdGgiOjE3OTIsIiRlbGVtZW50X3NlbGVjdG9yIjoiI2FwcCA+IHA6bnRoLW9mLXR5cGUoMSkgPiBhOm50aC1vZi10eXBlKDIpIiwiJGlzX2ZpcnN0X2RheSI6dHJ1ZX0sInR5cGUiOiJ0cmFjayIsImV2ZW50IjoiJFdlYkNsaWNrIiwiX3RyYWNrX2lkIjozMDExMDA3MTR9";
        String src = "eyJkaXN0aW5jdF9pZCI6IjE3ZmI5YjA0NTk5NTg1LTBiNjU5YTZiNTNhYWIwOC00NTU1NjYtMjAwNzA0MC0xN2ZiOWIwNDU5YTE1YiIsImxpYiI6eyIkbGliIjoianMiLCIkbGliX21ldGhvZCI6ImNvZGUiLCIkbGliX3ZlcnNpb24iOiIxLjEzLjEwIn0sInByb3BlcnRpZXMiOnsiJHNjcmVlbl9oZWlnaHQiOjExMjAsIiRzY3JlZW5fd2lkdGgiOjE3OTIsIiRsaWIiOiJqcyIsIiRsaWJfdmVyc2lvbiI6IjEuMTMuMTAiLCIkbGF0ZXN0X3JlZmVycmVyIjoidXJs55qEZG9tYWlu6Kej5p6Q5aSx6LSlIiwiJGxhdGVzdF9yZWZlcnJlcl9ob3N0IjoidXJs55qEZG9tYWlu6Kej5p6Q5aSx6LSlIiwiJGxhdGVzdF90cmFmZmljX3NvdXJjZV90eXBlIjoidXJs55qEZG9tYWlu6Kej5p6Q5aSx6LSlIiwiJGxhdGVzdF9zZWFyY2hfa2V5d29yZCI6InVybOeahGRvbWFpbuino+aekOWksei0pSIsIiRyZWZlcnJlciI6ImZpbGU6Ly8vVXNlcnMvaWNldGVhL215cHJvamVjdC9zZW5zb3JfdGVzdC9zYS1zZGstamF2YXNjcmlwdC0xLjIxLjEyL2RlbW8vdnVlL2luZGV4Lmh0bWwjL2ZvbyIsIiRyZWZlcnJlcl9ob3N0IjoiIiwiJHVybCI6ImZpbGU6Ly8vVXNlcnMvaWNldGVhL215cHJvamVjdC9zZW5zb3JfdGVzdC9zYS1zZGstamF2YXNjcmlwdC0xLjIxLjEyL2RlbW8vdnVlL2luZGV4Lmh0bWwjL2JhciIsIiR1cmxfcGF0aCI6Ii9Vc2Vycy9pY2V0ZWEvbXlwcm9qZWN0L3NlbnNvcl90ZXN0L3NhLXNkay1qYXZhc2NyaXB0LTEuMjEuMTIvZGVtby92dWUvaW5kZXguaHRtbCIsIiR0aXRsZSI6IlZ1ZSAmIFNlbnNvcnMgU0RLIiwiJGlzX2ZpcnN0X2RheSI6dHJ1ZSwiJGlzX2ZpcnN0X3RpbWUiOmZhbHNlfSwidHlwZSI6InRyYWNrIiwiZXZlbnQiOiIkcGFnZXZpZXciLCJfdHJhY2tfaWQiOjk0MzA3MTd9";
//        String src = "[{\n" +
//                "    token:123456,\n" +
//                "    gzip:1,\n" +
//                "}," + "{\n" +
//                "    token:123456,\n" +
//                "    gzip:1,\n" +
//                "}]";
        byte[] bytes = base64Decode(src);
        JSONObject jsonObject = JSONObject.parseObject(new String(bytes, "utf-8"));
        System.out.println(jsonObject);
    }

}
