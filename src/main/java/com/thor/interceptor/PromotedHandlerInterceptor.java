package com.thor.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * @author Lishaoyin
 * add method addRegistry() to promote the registration of interceptors
 */
public interface PromotedHandlerInterceptor extends HandlerInterceptor {

    /**
     * add interceptor registry in web mvc configurer
     * @param registry
     */
    void addRegistry(InterceptorRegistry registry);

}
