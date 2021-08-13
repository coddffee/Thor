package com.thor.util;

import com.thor.type.Identity;
import com.thor.type.Role;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InterceptorUtil {

    private static final String SIGN_IN_HTML_PATH = "/sign-in.html";
    private static final String NO_RIGHTS_HTML_PATH = "/no-rights.html";

    public static void addPatterns(InterceptorRegistry registry,
                                   HandlerInterceptor interceptor,
                                   String[] includePatterns,
                                   String[] excludePatterns) {
        InterceptorRegistration registration = registry.addInterceptor(interceptor);
        registration.addPathPatterns(includePatterns)
                .excludePathPatterns(excludePatterns);
    }

    public static boolean verifyAndRedirect(HttpServletRequest request,
                                            HttpServletResponse response,
                                            Role role) throws IOException {
        String contextPath = request.getContextPath();
        Identity identity = IdentifyUtil.readIdentity(request);
        if(identity == null) {
            response.sendRedirect(contextPath + SIGN_IN_HTML_PATH);
            return false;
        }
        if(!IdentifyUtil.verifyRole(request,role)) {
            response.sendRedirect(contextPath + NO_RIGHTS_HTML_PATH);
            return false;
        }
        return true;
    }

}
