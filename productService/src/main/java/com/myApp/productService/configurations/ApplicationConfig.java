package com.myApp.productService.configurations;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration //it tells that this class will have the @bean methods hence spring will execute all the beans methods
public class ApplicationConfig {
    @Bean //spring understands that only 1 object should be created of it in application context where spring store all it's objects
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

//  It takes key value pair
//  creating object of redis template
    @Bean
    public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory redisConnectionFactory){
       RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
       redisTemplate.setConnectionFactory(redisConnectionFactory);
       return redisTemplate;
    }

    @Bean
    @LoadBalanced//since user service has multiple instances running we want only 1 to be taken at time hence we use LoadBalanced annotation
    public RestTemplate loadBalancedRestTemplate() {
        return new RestTemplate();
    }

}
