package com.example.repeatdemo.core;

import com.example.repeatdemo.core.strategy.RedissonConfigStrategy;
import com.example.repeatdemo.core.strategy.impl.ClusterRedissonConfigStrategyImpl;
import com.example.repeatdemo.core.strategy.impl.StandaloneRedissonConfigStrategyImpl;
import com.example.repeatdemo.enums.RedisConnectionType;
import com.example.repeatdemo.prop.RedissonProperties;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.config.Config;

@Slf4j
public class RedissonManager {

    private Config config = new Config();

    private Redisson redisson = null;

    public RedissonManager() {
    }

    public RedissonManager(RedissonProperties redissonProperties) {
        //装配开关
        Boolean enabled = redissonProperties.getEnabled();
        if (enabled) {
            try {
                config = RedissonConfigFactory.getInstance().createConfig(redissonProperties);
                redisson = (Redisson) Redisson.create(config);
            } catch (Exception e) {
                log.error("Redisson初始化错误", e);
            }
        }
    }

    public Redisson getRedisson() {
        return redisson;
    }

    /**
     * Redisson连接方式配置工厂
     * 双重检查锁
     */
    static class RedissonConfigFactory {

        private RedissonConfigFactory() {
        }

        private static volatile RedissonConfigFactory factory = null;

        public static RedissonConfigFactory getInstance() {
            if (factory == null) {
                synchronized (Object.class) {
                    if (factory == null) {
                        factory = new RedissonConfigFactory();
                    }
                }
            }
            return factory;
        }

        /**
         * 根据连接类型創建连接方式的配置
         *
         * @param redissonProperties
         * @return Config
         */
        Config createConfig(RedissonProperties redissonProperties) {
            Preconditions.checkNotNull(redissonProperties);
            Preconditions.checkNotNull(redissonProperties.getAddress(), "redis地址未配置");
            RedisConnectionType connectionType = redissonProperties.getType();
            // 声明连接方式
            RedissonConfigStrategy redissonConfigStrategy = null;
            if (connectionType.equals(RedisConnectionType.SENTINEL)) {
//                redissonConfigStrategy = new SentinelRedissonConfigStrategyImpl();
            } else if (connectionType.equals(RedisConnectionType.CLUSTER)) {
                redissonConfigStrategy = new ClusterRedissonConfigStrategyImpl();
            } else if (connectionType.equals(RedisConnectionType.MASTERSLAVE)) {
//                redissonConfigStrategy = new MasterslaveRedissonConfigStrategyImpl();
            } else {
                redissonConfigStrategy = new StandaloneRedissonConfigStrategyImpl();
            }
            Preconditions.checkNotNull(redissonConfigStrategy, "连接方式创建异常");

            return redissonConfigStrategy.createRedissonConfig(redissonProperties);
        }
    }


}