package com.leftcoding.blog.setting;

import com.leftcoding.blog.utils.ViewHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by XdaTk on 16/5/22.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final String ENV_PRODUCTION = "production";

    private static final String ENV_DEVELOPMENT = "development";

    @Autowired
    private ViewHelper viewHelper;

    @Autowired
    private Environment env;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(viewObjectAddingInterceptor());
        super.addInterceptors(registry);
    }

    @PostConstruct
    public void registerJadeViewHelpers() {
        viewHelper.setApplicationEnv(this.getApplicationEnv());
    }

    @Bean
    public HandlerInterceptor viewObjectAddingInterceptor() {
        return new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                viewHelper.setStartTime(System.currentTimeMillis());

                return true;
            }

            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView view) {
                CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
                if (token != null) {
                    view.addObject(token.getParameterName(), token);
                }
            }
        };
    }

    public String getApplicationEnv() {
        return this.env.acceptsProfiles(ENV_PRODUCTION) ? ENV_PRODUCTION : ENV_DEVELOPMENT;
    }
}
