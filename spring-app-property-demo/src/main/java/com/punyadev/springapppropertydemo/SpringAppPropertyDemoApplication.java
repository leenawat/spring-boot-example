package com.punyadev.springapppropertydemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAppPropertyDemoApplication implements CommandLineRunner {

	@Value("${app.id}")
	private String appId;
	@Value("${app.name}")
	private String appName;
	public static void main(String[] args) {
		SpringApplication.run(SpringAppPropertyDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("app.id=" + appId);
		System.out.println("app.name=" + appName);
	}
}
