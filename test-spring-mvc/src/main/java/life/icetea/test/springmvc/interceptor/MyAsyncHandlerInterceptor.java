package life.icetea.test.springmvc.interceptor;

import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AsyncHandlerInterceptor提供了一个afterConcurrentHandlingStarted()方法,
 * 这个方法会在Controller方法异步执行时开始执行, 而Interceptor的postHandle方法则是需要等到Controller的异步执行完才能执行
 */
public class MyAsyncHandlerInterceptor implements AsyncHandlerInterceptor {

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("测试 AsyncHandlerInterceptor afterConcurrentHandlingStarted 执行");

    }

}
