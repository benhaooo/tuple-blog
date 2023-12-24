package com.hao;


import com.hao.domain.vo.ArticleVo;
import com.hao.service.ArticleService;
import com.hao.service.UploadService;
import com.overzealous.remark.Remark;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class crawler {
    private static final String BASE_URL = "https://www.didispace.com";

    private static Remark remark = new Remark();
    @Autowired
    private UploadService uploadService;

    @Autowired
    private ArticleService articleService;

    @Test
    public void crawler() throws IOException {
        Document doc = Jsoup.connect("https://www.didispace.com/timeline/").get();
        Elements elements = doc.select(".timeline-year-wrapper .timeline-item .vp-link");
        System.out.println(elements.size());
        int count = 0;
        for (Element element : elements) {
            if (count <= 20) {
                count++;
                continue;
            }
            if (count >= 60) {
                break;
            }
            String href = element.attr("href");
//        String href="/youtube/what-is-python.html";
            String link = BASE_URL + href;
            Document document = Jsoup.connect(link).get();
            Elements select = document.select(".theme-hope-content");
            Element contentEl = select.get(1);
            contentEl.children().last().remove();
            String content = contentEl.html();
            String title = document.select("meta[property=og:title]").first().attr("content");
            ;
            String category = document.select("meta[property=articleSection]").first().attr("content");
            List<String> tags = Arrays.asList(document.select("meta[property=keywords]").first().attr("content").split(","));
            String url = uploadService.uploadRandomImg().getData().toString();

            ArticleVo articleVo = ArticleVo.builder().content(remark.convert(content)).title(title).categoryName(category).tagNameList(tags).thumbnail(url).originalUrl(link).build();

            articleService.saveOrUpdateArticle(articleVo);

            count++;
        }


    }


}
