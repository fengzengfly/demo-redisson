package com.xiaofengstu.demoredisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RedissonConfig
 * @Author fengzeng
 * @Date 2022/8/25 0025 14:07
 */
@Configuration
public class RedissonConfig {

  @Bean
  public RedissonClient getRedissonClient() {
    Config config = new Config();
    config.useSingleServer().setAddress("redis://localhost:6379");
    return Redisson.create(config);
  }

}
