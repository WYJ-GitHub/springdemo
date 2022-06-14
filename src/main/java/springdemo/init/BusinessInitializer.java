package springdemo.init;


import springdemo.utils.RedisCacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 业务初始化器，可在此处做一些项目启动的初始化操作
 *
 */
@Slf4j
@Component
public class BusinessInitializer implements ApplicationRunner {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Override
	public void run(ApplicationArguments args) {
		RedisCacheUtil.init(redisTemplate);
	}
}
