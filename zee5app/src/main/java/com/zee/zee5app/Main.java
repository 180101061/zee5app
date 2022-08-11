package com.zee.zee5app;

import java.time.LocalDate;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zee.zee5app.config.Config;
import com.zee.zee5app.dto.User;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.repo.UserRepo;
import com.zee.zee5app.service.UserService;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ApplicationContext applicationContext = new 
				AnnotationConfigApplicationContext(Config.class);
		
		UserService userService = applicationContext.getBean(UserService.class);
		//UserService userService2 = applicationContext.getBean(UserService.class);
		//System.out.println(userService==userService2);
		try {
			userService.insertuser(new User("akkuA","kumari","akkui@zee.com",LocalDate.now(),LocalDate.of(1997, 12, 29)));
		} catch (UnableToGenerateIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(userService!=null);
		
//		UserRepo userRepo = applicationContext.getBean(UserRepo.class);
//		System.out.println(userRepo!=null);
//		Properties properties = applicationContext.getBean("properties",Properties.class);
//		//("properties",Properties.class) we provide the type 
//		System.out.println(properties);
//		Properties properties2 = applicationContext.getBean("properties",Properties.class);
//		//("properties",Properties.class) we provide the type 
//		System.out.println(properties2);
//		System.out.println(properties==properties2);
//		System.out.println(properties.hashCode());
//		System.out.println(properties2.hashCode());
	}

}
