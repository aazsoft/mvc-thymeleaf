package com.aazsoft.mvc.rest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class RestSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO: configure the security for the RESTs later
		http.csrf().disable().authorizeRequests().anyRequest().permitAll();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
	}

	@Bean
	FilterRegistrationBean corsFilter(
			@Value("${tagit.origin:http://localhost:8090}") String origin) {
		return new FilterRegistrationBean(new Filter() {
			public void doFilter(ServletRequest req, ServletResponse res,
					FilterChain chain) throws IOException, ServletException {
				HttpServletRequest request = (HttpServletRequest) req;
				HttpServletResponse response = (HttpServletResponse) res;
				String method = request.getMethod();
				// this origin value could just as easily have come from a
				// database
				response.setHeader("Access-Control-Allow-Origin", "*");
				response.setHeader("Access-Control-Allow-Methods",
						"POST,GET,OPTIONS,DELETE");
				response.setHeader("Access-Control-Max-Age",
						Long.toString(60 * 60));
				response.setHeader("Access-Control-Allow-Credentials", "true");
				response.setHeader(
						"Access-Control-Allow-Headers",
						"Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
				if ("OPTIONS".equals(method)) {
					response.setStatus(HttpStatus.OK.value());
				} else {
					chain.doFilter(req, res);
				}
			}

			public void init(FilterConfig filterConfig) {
			}

			public void destroy() {
			}
		});
	}
}
