package com.cognizant.authorization;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cognizant.authorization.models.Role;
import com.cognizant.authorization.models.User;
import com.cognizant.authorization.models.UserRole;
import com.cognizant.authorization.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@AllArgsConstructor
public class AuthorizationSeriveApplication implements CommandLineRunner {
	private UserService userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(AuthorizationSeriveApplication.class, args);
	}

	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		log.info("starting");
		User user=new User();
		user.setFirstName("Rahul");
		user.setLastName("Maithani");
		user.setUsername("rahul15");
		user.setPassword(bCryptPasswordEncoder.encode("test123"));
		user.setEmail("vaibhav.maithani15@gmail.com");
		user.setPhone("7065158949");
		user.setProfile("default.png");
		
		Role role=new Role();
		role.setRoleId(44L);
		role.setRoleName("ADMIN");

		Set<UserRole> userRoleSet=new HashSet<UserRole>();
		UserRole userRole=new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		userRoleSet.add(userRole);
		User user1=userService.createUser(user,userRoleSet);
//		log.info("{USER}",user1);
		User u2=new User();
		u2.setFirstName("Vaibhav");
		u2.setLastName("Maithani");
		u2.setUsername("vaibhav15");
		u2.setPassword(bCryptPasswordEncoder.encode("test123"));
		u2.setEmail("rahul.maithani15@gmail.com");
		u2.setPhone("9876543210");

		u2.setProfile("default.png");
		
		Role r2=new Role();
		r2.setRoleId(45L);
		r2.setRoleName("NORMAL");

		Set<UserRole> urs=new HashSet<UserRole>();
		UserRole ur=new UserRole();
		ur.setRole(r2);
		ur.setUser(u2);
		urs.add(ur);
		User user2=userService.createUser(u2,urs);
	}

}
