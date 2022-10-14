package com.kiddiesave.kiddiesave;

import com.kiddiesave.kiddiesave.entity.Role;
import com.kiddiesave.kiddiesave.entity.UserType;
import com.kiddiesave.kiddiesave.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = "com.kiddiesave.kiddiesave.*")
@Configuration
@EntityScan("com.kiddiesave.kiddiesave.entity") // path of the entity model
@EnableJpaRepositories("com.kiddiesave.kiddiesave.repository") //path  of jpa repository
public class KiddiesaveApplication implements CommandLineRunner {
	/*@Value("${spring.mail.host}")
	private String host;
	@Value("${spring.mail.port}")
	private String port;
	@Value("${spring.mail.username}")
	private String username;
	@Value("${spring.mail.password}")
	private String password;*/

	//private Environment env;

	// Autowire RoleRepository
	private RoleRepository roleRepository;

	@Autowired
	public KiddiesaveApplication(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	/*public JavaMailSender getMailSender()
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
	}*/
	@Override
	public void run(String... args) throws Exception {
		// Add implementation to add new roles in Kiddiesave database
		// Check if roles exist
		// if no, create
		// end

		List<Role> existingRoles = roleRepository.findAll();
		if(existingRoles != null)
		{
			// do nothing
		}
		List<UserType> addRoles = new ArrayList<>();
		addRoles.add(UserType.KIDDIESAVE_PARENT);
		addRoles.add(UserType.KIDDIESAVE_CHILD);
		addRoles.add(UserType.ROLE_ADMIN);

		for(UserType roles:addRoles)
		{
			Role r = new Role();
			r.setName(roles);
			roleRepository.save(r);
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(KiddiesaveApplication.class, args);
	}

}
