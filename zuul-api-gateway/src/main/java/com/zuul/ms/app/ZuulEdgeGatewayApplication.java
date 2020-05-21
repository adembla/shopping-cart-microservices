package com.zuul.ms.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
@EnableAuthorizationServer
public class ZuulEdgeGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulEdgeGatewayApplication.class, args); 
	}
}
