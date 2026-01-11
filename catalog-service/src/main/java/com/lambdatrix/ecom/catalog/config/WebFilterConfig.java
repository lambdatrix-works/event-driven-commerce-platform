package com.lambdatrix.ecom.catalog.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lambdatrix.ecom.catalog.config.filter.CorrelationIdFilter;

@Configuration
public class WebFilterConfig {

  @Bean
  FilterRegistrationBean<CorrelationIdFilter> correlationIdFilter() {
    FilterRegistrationBean<CorrelationIdFilter> bean = new FilterRegistrationBean<>();
    bean.setFilter(new CorrelationIdFilter());
    bean.setOrder(1);
    return bean;
  }
}
