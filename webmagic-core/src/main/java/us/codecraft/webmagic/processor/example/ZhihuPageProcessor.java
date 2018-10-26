package us.codecraft.webmagic.processor.example;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author code4crafter@gmail.com <br>
 * @since 0.6.0
 */
public class ZhihuPageProcessor implements PageProcessor {

	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

	@Override
	public void process(Page page) {
		page.addTargetRequests(
				page.getHtml().links().regex("https://www\\.intuan\\.com/product/makeup/\\d+.*").all().subList(0, 1));
		// page.putField("price",
		// page.getHtml().xpath("//p[@class='orderHb_s1_p']").toString());
		page.putField("title", page.getHtml().xpath("//h3[@class='orderHb_s1_tit']//tidyText()").toString());
		// page.putField("answer",
		// page.getHtml().xpath("//div[@class='QuestionAnswer-content']/tidyText()").toString());
		// if (page.getResultItems().get("title") == null) {
		// // skip this page
		// page.setSkip(true);
		// }
	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new ZhihuPageProcessor()).addUrl("https://www.intuan.com/article-1").run();
	}
}