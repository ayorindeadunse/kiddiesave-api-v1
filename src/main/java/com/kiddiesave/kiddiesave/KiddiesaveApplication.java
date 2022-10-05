package com.kiddiesave.kiddiesave;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
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
	@Value("${spring.mail.host}")
	private String host;
	@Value("${spring.mail.port}")
	private String port;
	@Value("${spring.mail.username}")
	private String username;
	@Value("${spring.mail.password}")
	private String password;

	//private Environment env;

	public KiddiesaveApplication() {
	}

	public JavaMailSender getMailSender()
	{
		// java mail inmplementation
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(host);
		mailSender.setPort(Integer.valueOf(port));
		mailSender.setUsername(username);
		mailSender.setPassword(password);

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable","true");
		javaMailProperties.put("mail.smtp.auth","true");
		javaMailProperties.put("mail.transport.protocol","smtp");
		//javaMailProperties.put( "mail.smtp.ssl.enable",true);
		javaMailProperties.put("mail.debug","true");

		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}
	public static void main(String[] args) {
		SpringApplication.run(KiddiesaveApplication.class, args);
	}

}
