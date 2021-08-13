package com.thor.interceptor;

import com.thor.type.Role;
import com.thor.util.InterceptorUtil;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NotSystemManagerInterceptor implements PromotedHandlerInterceptor {

    private final String[] includePatterns = {
            "/system-manager/**"
    };
    private final String[] excludePatterns = {
            "/system-manager/sign-in"
    };

    public NotSystemManagerInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        return InterceptorUtil.verifyAndRedirect(request,response,Role.SYSTEM_MANAGER);
    }

    @Override
    public void addRegistry(InterceptorRegistry registry) {
        InterceptorUtil.addPatterns(registry,this,includePatterns,excludePatterns);
    }
}
