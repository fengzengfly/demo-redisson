package com.xiaofengstu.demoredisson.service;

/**
 * The interface Redisson service.
 *
 * @ClassName RedissonService
 * @Author fengzeng
 * @Date 2022 /8/25 0025 14:03
 */
public interface RedissonService {
  /**
   * Gets ticket.
   *
   * @param userId the user id
   * @return the ticket
   */
  boolean getTicket(String userId);
}
