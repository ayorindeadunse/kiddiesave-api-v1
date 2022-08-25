package com.kiddiesave.kiddiesave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@SpringBootApplication
@Configuration
@EntityScan("com.kiddiesave.Kiddiesave.entity") // path of the entity model
@EnableJpaRepositories("com.kiddiesave.kiddiesave.repository") //path  of jpa repository
public class KiddiesaveApplication {


	private Environment env;

	public KiddiesaveApplication(Environment env) {
		this.env = env;
	}

	public JavaMailSender getMailSender()
	{
		// java mail inmplementation
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(env.getProperty("spring.mail.host"));
		mailSender.setPort(Integer.valueOf(env.getProperty("spring.mail.port")));
		mailSender.setUsername(env.getProperty("spring.mail.username"));
		mailSender.setPassword(env.getProperty("spring.mail.password"));

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable","true");
		javaMailProperties.put("mail.smtp.auth","true");
		javaMailProperties.put("mail.transport.protocol","smtp");
		javaMailProperties.put("mail.debug","true");

		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}
	public static void main(String[] args) {
		SpringApplication.run(KiddiesaveApplication.class, args);
	}

}
