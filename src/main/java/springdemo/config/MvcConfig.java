package springdemo.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.method.HandlerMethod;
import springdemo.core.annotation.LoginRequired;
import java.io.PrintWriter;
import springdemo.utils.SessionUtil;
import springdemo.entity.User;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	/**
	 * 拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new HandlerInterceptor() {
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception {
				if (handler instanceof HandlerMethod) {
					HandlerMethod handlerMethod = (HandlerMethod) handler;
					LoginRequired loginRequired = handlerMethod.getMethodAnnotation(LoginRequired.class);
					if (null == loginRequired) {
						return true;
					}
					// 预请求
		            if (RequestMethod.OPTIONS.name().equals(request.getMethod())) {
						return true;
					}
				    User user = SessionUtil.getUser(request);
					if (user == null) {
						response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		                response.setHeader("Access-Control-Allow-Methods", "*");
		                response.setHeader("Access-Control-Max-Age", "3600");
		                response.setHeader("Access-Control-Allow-Credentials", "true");
		                response.setContentType("application/json; charset=utf-8");
		                response.setCharacterEncoding("utf-8");
		                PrintWriter pw = response.getWriter();
		                pw.write("{\"code\":" + HttpServletResponse.SC_UNAUTHORIZED + ",\"status\":\"no\",\"msg\":\"无授权访问，请先登录\"}");
		                pw.flush();
		                pw.close();
		                return false;
					}
				}
				return true;

			}
		}).addPathPatterns("/**").excludePathPatterns("/login", "/register", "/login/doLogin", "/user/register",
				"/mystatic/**", "/druid/**", "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
	}

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
