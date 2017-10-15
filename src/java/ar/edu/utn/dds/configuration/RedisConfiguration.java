package ar.edu.utn.dds.configuration;

import ar.edu.utn.dds.cache.CacheData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.DefaultRedisCachePrefix;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCachePrefix;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
public class RedisConfiguration {
	@Value("${redis.url}")
	private String host;
	private Integer port = 6379;
	private Integer ttl = 3600;

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(host);
		factory.setPort(port);
		factory.setUsePool(true);
		return factory;
	}
	@Bean
	public RedisTemplate<?, ?> redisTemplate() {
		RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}

	@Bean
	public RedisCacheManager redisCacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate(), CacheData.getAllCacheNames());
		redisCacheManager.initializeCaches();
		redisCacheManager.setDefaultExpiration(ttl);
		redisCacheManager.setCachePrefix(new DefaultRedisCachePrefix("cached"));
		redisCacheManager.setUsePrefix(true);
		return redisCacheManager;
	}

	@Bean
	public RedisMessageListenerContainer listenerContainer(RedisConnectionFactory connectionFactory) {
		RedisMessageListenerContainer listenerContainer = new RedisMessageListenerContainer();
		listenerContainer.setConnectionFactory(connectionFactory);
		return listenerContainer;
	}
}
