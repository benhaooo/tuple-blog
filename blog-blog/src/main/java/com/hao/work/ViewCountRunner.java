package com.hao.work;

import com.hao.constants.SystemConstants;
import com.hao.mapper.ArticleMapper;
import com.hao.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ViewCountRunner implements CommandLineRunner {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
//        项目启动时将所有的浏览量读取到redis中
        List<Map<Long,Long>> maps= articleMapper.listViewCount();
        for(Map<Long,Long> map : maps){
            redisCache.setCacheMapValue(SystemConstants.REDIS_ARTICLE_VIEW_COUNT,String.valueOf(map.get("id")),map.get("view_count"));
        }
    }
}
