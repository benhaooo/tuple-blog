package com.hao.work;


import com.hao.constants.SystemConstants;
import com.hao.domain.entity.Article;
import com.hao.mapper.ArticleMapper;
import com.hao.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AutoTask {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    RedisCache redisCache;


    //    定时任务：更新浏览量到数据库
    @Scheduled(cron = "0 0/30 * * * ?")
    public void updateViewCount() {
        Map<String, String> viewCountMap = redisCache.getCacheMap(SystemConstants.REDIS_ARTICLE_VIEW_COUNT);
        for (Map.Entry<String, String> entry : viewCountMap.entrySet()) {
            Article article = Article.builder().id(Long.valueOf(entry.getKey())).viewCount(Long.valueOf(entry.getValue())).build();
            articleMapper.updateById(article);
        }
    }
}
