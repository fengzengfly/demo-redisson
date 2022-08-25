package com.xiaofengstu.demoredisson.controller;

import com.xiaofengstu.demoredisson.service.RedissonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName RedissionController
 * @Author fengzeng
 * @Date 2022/8/25 0025 13:57
 */
@RestController
public class RedissonController {
  @Resource(name = "redissonService")
  RedissonService redissonService;

  @GetMapping("/test")
  public String getTest() throws InterruptedException {
    AtomicInteger count = new AtomicInteger();
    CountDownLatch countDownLatch = new CountDownLatch(100);
    for (int i = 0; i < 100; i++) {
      final int k = i;
      new Thread(() -> {
        String userId = "user" + k;
        boolean success = redissonService.getTicket(userId);
        if (success) {
          count.getAndIncrement();
        }
        countDownLatch.countDown();
        System.out.println("success count: " + count);
        System.out.println("count down latch count: "+ countDownLatch.getCount());
      }).start();
    }
    countDownLatch.await();

    return "success: " + count;
  }
}
