package com.thor;

import com.thor.interceptor.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public WebMvcConfig() {
        super();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        new SignUpAndSignInInterceptor().addRegistry(registry);
        new NotSystemManagerInterceptor().addRegistry(registry);
        new NotManualInspectionManagerInterceptor().addRegistry(registry);
        new NotAutomaticInspectionManagerInterceptor().addRegistry(registry);
        new NotEquipmentManagerInterceptor().addRegistry(registry);
        new NotInspectorInterceptor().addRegistry(registry);
    }
}
