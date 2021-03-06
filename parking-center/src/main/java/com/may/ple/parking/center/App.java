package com.may.ple.parking.center;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class App extends SpringBootServletInitializer{
	private static final Logger LOG = Logger.getLogger(App.class.getName());
	
	public static void main(String[] args) {
		LOG.info(":---------: Start by main method :----------:");
		SpringApplication.run(App.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		LOG.info(":----------: Start by SpringBootServletInitializer :----------:");
		return builder.sources(App.class);
	}

	@PostConstruct
	public void init() {
		LOG.info(":----------: Start Ricoh application :----------:");
	}

}
