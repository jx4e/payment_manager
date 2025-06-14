package me.jx4e.paymentmanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.expression.Mvc;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("/login");
        registry.addViewController("/dashboard").setViewName("/dashboard");
        registry.addViewController("/statements").setViewName("/statements");
        registry.addViewController("/schedule").setViewName("/schedule");
        registry.addViewController("/settings").setViewName("/settings");
    }
}
