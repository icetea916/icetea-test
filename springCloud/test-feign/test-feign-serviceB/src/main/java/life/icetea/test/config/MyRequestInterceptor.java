package life.icetea.test.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import javax.validation.constraints.Size;

/**
 * @author icetea
 */
public class MyRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String url = requestTemplate.url();
        System.out.println("feign拦截器获取url={}" + url);
    }

}
