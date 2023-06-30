package life.icetea.test.springmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("test-aync-handler-interceptor")
public class TestAsyncHandlerInterceptorController {

    @RequestMapping("test")
    public DeferredResult<String> test() {
        DeferredResult<String> dr = new DeferredResult<String>(3000L, "超时了");

        new Thread(() -> {
            try {
                Thread.sleep(5000L);
                dr.setResult("异步执行完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        return dr;
    }

}
