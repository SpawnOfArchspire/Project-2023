package com.ascendingdc.learnrestapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@ServletComponentScan
public class LearnrestapiApplication extends SpringBootServletInitializer {

	private static final Logger logger = LoggerFactory.getLogger(LearnrestapiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LearnrestapiApplication.class, args);

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(LearnrestapiApplication.class);
	}

}
