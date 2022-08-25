package com.xiaofengstu.demoredisson.service.impl;

import com.xiaofengstu.demoredisson.service.RedissonService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedissonServiceImpl
 * @Author fengzeng
 * @Date 2022/8/25 0025 14:00
 */
@Service("redissonService")
public class RedissonServiceImpl implements RedissonService {
  private static int STOCK = 50;

  @Resource
  private RedissonClient redissonClient;

  @Override
  public boolean getTicket(String userId) {
    RLock lock = redissonClient.getLock("count");
    try {
      lock.tryLock();
      lock.lock();
      if (STOCK > 0) {
        STOCK--;
        System.out.println(userId + " get ticket,ticket have " + STOCK + " 张");
        return true;
      } else {
        System.out.println("扣取库存失败，库存不足！！！");
        return false;
      }
    } finally {
      if (lock.isHeldByCurrentThread()) {
        lock.unlock();
      }
    }
  }
}
