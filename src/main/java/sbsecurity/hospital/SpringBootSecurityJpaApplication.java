package sbsecurity.hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootApplication
public class SpringBootSecurityJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityJpaApplication.class, args);
	}

}