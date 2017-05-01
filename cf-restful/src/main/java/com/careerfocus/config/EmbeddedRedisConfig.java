package com.careerfocus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.ExpiringSession;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
public class EmbeddedRedisConfig {

	private static String HOST = "localhost";
	private static int PORT = 6379;

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
		jedisConFactory.setHostName(HOST);
		jedisConFactory.setPort(PORT);
		jedisConFactory.afterPropertiesSet();
		return jedisConFactory;
	}

	// @Bean
	// public RedisTemplate<String, String> redisTemplate() {
	// RedisTemplate<String, String> template = new RedisTemplate<String,
	// String>();
	// template.setConnectionFactory(jedisConnectionFactory());
	// template.setKeySerializer(new StringRedisSerializer());
	// template.setHashValueSerializer(new
	// GenericToStringSerializer<Object>(Object.class));
	// template.setValueSerializer(new
	// GenericToStringSerializer<Object>(Object.class));
	// template.setExposeConnection(true);
	// template.afterPropertiesSet();
	// return template;
	// }

	@Bean
	public RedisTemplate<String, ExpiringSession> redisTemplate() throws Exception {
		RedisTemplate<String, ExpiringSession> template = new RedisTemplate<>();
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}

	// @Autowired
	// private FindByIndexNameSessionRepository sessionRepository;
	//
	// @Bean
	// @SuppressWarnings("unchecked")
	// public SpringSessionBackedSessionRegistry sessionRegistry() {
	// return new SpringSessionBackedSessionRegistry(this.sessionRepository);
	// }
}
