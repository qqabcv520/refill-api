package cn.mifan123.refill.config;

import cn.mifan123.refill.common.constant.Constants;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by 米饭 on 2017-05-23.
 */
@Configuration
public class CacheConfig {

    /*
     * cache管理器
	 */
    @Bean
    public CacheManager cacheCacheManager() {
        return new EhCacheCacheManager(cacheManagerFactoryBean().getObject());
    }

    /*
     * 据shared与否的设置,Spring分别通过CacheManager.create()或new CacheManager()方式来创建一个ehcache基地.
     */
    @Bean
    public EhCacheManagerFactoryBean cacheManagerFactoryBean() {
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        cacheManagerFactoryBean.setShared(true);
        return cacheManagerFactoryBean;
    }


    @Bean
    public Cache tokenCache(CacheManager cacheManager) {
        return cacheManager.getCache(Constants.TOKEN_CACHE_NAME);
    }

}