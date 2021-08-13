package com.thor.interceptor;

import com.thor.type.Role;
import com.thor.util.InterceptorUtil;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NotAutomaticInspectionManagerInterceptor implements PromotedHandlerInterceptor {

    private final String[] includePatterns = {
            "/automatic-inspection-manager/**"
    };
    private final String[] excludePatterns = {};

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        return InterceptorUtil.verifyAndRedirect(request,response,
                Role.AUTOMATIC_INSPECTION_MANAGER);
    }

    @Override
    public void addRegistry(InterceptorRegistry registry) {
        InterceptorUtil.addPatterns(registry,this,includePatterns,excludePatterns);
    }
}
