package com.tradeone.config;

import com.tradeone.service.InvestorService;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.SessionManagementFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  CorsFilter corsFilter() {
    CorsFilter filter = new CorsFilter();
    return filter;
  }

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private InvestorService investorService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .addFilterBefore(corsFilter(), SessionManagementFilter.class)
        .authorizeRequests()
        .antMatchers("/activate/*"
                ,"/api"
            ,"/validationName"
            ,"/validationEmail"
            , "/name",
            "/static/**", "/registration/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .defaultSuccessUrl("/cabinet.html", true)
        .loginPage("/")
        .permitAll()
        .and()
        .logout().invalidateHttpSession(true)
        .permitAll()
        .and()
        .csrf().disable();
  }


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(investorService)
        .passwordEncoder(passwordEncoder);
  }
}

class CorsFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    HttpServletRequest request = (HttpServletRequest) servletRequest;

    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
    response.setHeader("Access-Control-Allow-Headers", "*");
    //response.setHeader("Access-Control-Allow-Credentials", "true");
//    response.setHeader("Access-Control-Max-Age", 180);
    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {

  }
}