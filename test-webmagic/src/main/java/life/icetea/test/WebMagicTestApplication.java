package life.icetea.test;

import us.codecraft.webmagic.Spider;

/**
 * @author icetea
 * @date 2022-10-22 15:39
 */
public class WebMagicTestApplication {

    public static void main(String[] args) {
        MyPageProcessor myPageProcessor = new MyPageProcessor();

        Spider spider = Spider.create(myPageProcessor);
        // 设置起始url
        spider.addUrl("http://zx.500.com/jczq/worth/index.php?d=2022-10-22")
//                .start() // 异步执行
                .run(); // 同步执行
    }

}
