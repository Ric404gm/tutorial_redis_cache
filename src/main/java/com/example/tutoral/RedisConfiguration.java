package com.example.tutoral;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;


@Configuration
@PropertySource("classpath:application.properties")
public class RedisConfiguration {


    @Value("${redis.host}")
    private String host;

    
    @Value("${redis.port}")
    private int port;


    @Bean
    public LettuceConnectionFactory loadRedisConnection(){

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host,port);
        return new LettuceConnectionFactory(redisStandaloneConfiguration );

    }
    
    @Bean
    public RedisCacheManager  loRedisCacheManager (){
        RedisCacheConfiguration cacheConfig = myDefaultCacheConfig(Duration.ofMinutes(10)).disableCachingNullValues();

        return  RedisCacheManager.builder(loadRedisConnection())
        .cacheDefaults(cacheConfig)
        .withCacheConfiguration("tutorials", myDefaultCacheConfig(Duration.ofMinutes(15)))
        .withCacheConfiguration("tutorial", myDefaultCacheConfig(Duration.ofMinutes(20)))
        .withCacheConfiguration("published_tutorials" ,myDefaultCacheConfig(Duration.ofMinutes(20)))
        .build();

    }

    private RedisCacheConfiguration myDefaultCacheConfig(java.time.Duration duration) {
            return RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(duration)
                .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    /**
     *   @Bean
        public RedisCacheManager cacheManager() {
            RedisCacheConfiguration cacheConfig = myDefaultCacheConfig(Duration.ofMinutes(10)).disableCachingNullValues();

            return RedisCacheManager.builder(redisConnectionFactory())
                .cacheDefaults(cacheConfig)
                .withCacheConfiguration("tutorials", myDefaultCacheConfig(Duration.ofMinutes(5)))
                .withCacheConfiguration("tutorial", myDefaultCacheConfig(Duration.ofMinutes(1)))
                .build();
        }

        private RedisCacheConfiguration myDefaultCacheConfig(Duration duration) {
            return RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(duration)
                .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        }

     */

}
