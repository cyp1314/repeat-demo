package com.example.repeatdemo.core.strategy;

import com.example.repeatdemo.prop.RedissonProperties;
import org.redisson.config.Config;

/**
 * Redisson配置构建接口
 *
 * @author zyf
 * @date 2020-11-11
 */
public interface RedissonConfigStrategy {

	/**
	 * 根据不同的Redis配置策略创建对应的Config
	 *
	 * @param redissonProperties
	 * @return Config
	 */
	Config createRedissonConfig(RedissonProperties redissonProperties);
}