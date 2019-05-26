package com.tradeone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

  @Bean
  public ViewResolver getViewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/static/");
    resolver.setSuffix(".html");
    return resolver;
  }

  @Override
  public void configureDefaultServletHandling(
      DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/static/**")
        .addResourceLocations("classpath:/static/");
  }



    public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("/").setViewName("index");
      registry.addViewController("/index").setViewName("index");

    }
  }
