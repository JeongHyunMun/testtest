package com.example.demo.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfig {
	
	@Value("${spring.data.redis.host}")
	private String redisHost;
	
	@Value("${spring.data.redis.port}")
	private int redisPort;

    @Value("${spring.data.redis.password:}")
    private String redisPassword;
	
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
    	RedisStandaloneConfiguration config =
    			new RedisStandaloneConfiguration(redisHost, redisPort);
    	
    	if (!redisPassword.isBlank()) {
    		config.setPassword(redisPassword);
    		}
    	
    	LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
    			.commandTimeout(java.time.Duration.ofSeconds(5))
    			.shutdownTimeout(java.time.Duration.ZERO)
    			.build();
    	
    	return new LettuceConnectionFactory(config, clientConfig);
    	
    }
    
    /** Refresh Token 저장용 (문자열만 저장하는 Template) */
    @Bean
    public StringRedisTemplate stringRedisTemplate(LettuceConnectionFactory connectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(connectionFactory);
        return template;
    }

    /** 일반 캐시/객체 저장용 Template */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory connectionFactory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Key는 문자열
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        // Value는 JSON 직렬화
        Jackson2JsonRedisSerializer<Object> jsonSerializer =
                new Jackson2JsonRedisSerializer<>(Object.class);

        template.setValueSerializer(jsonSerializer);
        template.setHashValueSerializer(jsonSerializer);

        return template; 
    }
    
}
