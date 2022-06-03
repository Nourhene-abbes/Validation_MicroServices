package com.esprit.getway.communication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.esprit.getway.filters.ErrorFilter;
import com.esprit.getway.filters.PostFilter;
import com.esprit.getway.filters.PreFilter;
import com.esprit.getway.filters.RouteFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class GetwayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetwayServerApplication.class, args);
	}

	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}

	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}

	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}

	@Bean
	public RouteFilter RouteFilter() {
		return new RouteFilter();
	}
}
