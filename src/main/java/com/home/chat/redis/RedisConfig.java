package com.home.chat.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: yanyj
 * @Description: TODO
 * @Create By: 2020年3月18日 - 下午2:27:41
 * @Version: 1.0.1
 *
 */
@Slf4j
@Configuration
public class RedisConfig {
	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.timeout}")
	private int timeout;

	@Value("${spring.redis.jedis.pool.max-idle}")
	private int maxIdle;

	@Value("${spring.redis.jedis.pool.max-wait}")
	private long maxWaitMillis;

	@Value("${spring.redis.password}")
	private String password;

	@Bean(name = "stringRedisTemplate")
	public StringRedisTemplate getStringRedisTemplate(RedisConnectionFactory connectionFactory) {
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
		stringRedisTemplate.setConnectionFactory(connectionFactory);
		// 由于支持redis事务，所以获取的时候，需要用exec获取结果
//		stringRedisTemplate.setEnableTransactionSupport(true);
		stringRedisTemplate.setHashValueSerializer(new StringRedisSerializer());
		return stringRedisTemplate;

	}

	@Bean
	public JedisPool redisPoolFactory() throws Exception {
		log.debug("JedisPool注入成功！！");
		log.debug("redis地址：" + host + ":" + port);
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
//		// 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
//		jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
		// 是否启用pool的jmx管理功能, 默认true
		jedisPoolConfig.setJmxEnabled(true);
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
		return jedisPool;
	}

	@Bean
	public Jedis getJedis(JedisPool pool) {
		return pool.getResource();
	}
	
//	@Bean
//	public RedisMessageListenerContainer getRedisMessageListenerContainer(RedisConnectionFactory factory) {
//		RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
//		redisMessageListenerContainer.setConnectionFactory(factory);
////		EnumMessageChannel[] channels = EnumMessageChannel.values();
////		for (EnumMessageChannel enumMessageChannel : channels) {
////			redisMessageListenerContainer.addMessageListener(getMessageListenerAdapter(), new PatternTopic(enumMessageChannel.getChannelName()));
////		}
//
//		redisMessageListenerContainer.addMessageListener( getMessageListenerAdapter(), new PatternTopic(EnumMessageChannel.refresh_cache.getChannelName()));
//		return redisMessageListenerContainer;
//	}
	
//	@Bean
//	@Scope("prototype")
//	public MessageListenerAdapter getMessageListenerAdapter() {
//		return new MessageListenerAdapter(new RedisSubscriber(), "receiveMessage");
//	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
		// 创建 RedisTemplate 对象
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		// 设置连接工厂
		template.setConnectionFactory(connectionFactory);
		// 创建 JSON 序列化工具
		GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
		// 设置 key 的序列化
		template.setKeySerializer(RedisSerializer.string());
		template.setHashKeySerializer(RedisSerializer.string());
		// 设置 value 的序列化
		template.setValueSerializer(jsonRedisSerializer);
		template.setHashValueSerializer(jsonRedisSerializer);
		// 返回
		return template;
	}
}
