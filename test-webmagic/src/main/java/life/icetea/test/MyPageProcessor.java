package life.icetea.test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

/**
 * @author icetea
 * @date 2022-10-22 15:35
 */
public class MyPageProcessor implements PageProcessor {

    /**
     * 页面分析
     *
     * @param page 下载结果封装成Page对象
     */
    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        Selectable xpath = html.xpath("//tr[@class='wdls']");
        System.out.println(xpath);

    }

    /**
     * 站点配置返回一个站点站点对象
     * 返回默认配置使用Site.me()创建一个site对象
     *
     * @return
     */
    @Override
    public Site getSite() {
        return Site.me().setCharset("gb2312").me();
    }
}
