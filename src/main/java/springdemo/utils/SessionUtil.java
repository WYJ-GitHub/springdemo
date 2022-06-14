package springdemo.utils;
import springdemo.entity.User;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @ClassName SessionUtil
 * @Author zrx
 * @Date 2021/7/20 10:10
 */
public class SessionUtil {

	private static final String SESSION_USER_TOKEN_KEY = "token";

	/**
	 * 登录
	 * @param user
	 */
    public static void onLogin(User user) {
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        user.setToken(token);
        RedisCacheUtil.addAdminByToken(token, user);
	}

	/**
	 * 退出
	 * @param request
	 */
	public static void logOut(HttpServletRequest request) {
        String token = request.getHeader(SESSION_USER_TOKEN_KEY);
        RedisCacheUtil.del(token);
	}

	/**
	 * 获取当前用户
	 * @param request
	 * @return
	 */
    public static User getUser(HttpServletRequest request) {
		String token = request.getHeader(SESSION_USER_TOKEN_KEY);
    User adminByToken = RedisCacheUtil.getAdminByToken(token, User.class);
		//重置token过期时长
		if (adminByToken != null) {
			RedisCacheUtil.expire(token, RedisCacheUtil.SESSION_EXPIRE_TIME);
		}
		return adminByToken;
	}
}

