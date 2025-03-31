package com.sonnguyen.redis_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();

//        create and manager connection to redis
        template.setConnectionFactory(redisConnectionFactory);

//      serialize key trước khi bất kì thao tác nào lên redis
        template.setKeySerializer(new StringRedisSerializer()); //Xác định cách Redis lưu key.
        template.setHashKeySerializer(new StringRedisSerializer()); // Xác định cách Redis lưu hash key.

        // Serializer cho value (JSON) trước operation
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        template.afterPropertiesSet(); // Kiểm tra và hoàn tất thiết lập
        return template;
    }
}
