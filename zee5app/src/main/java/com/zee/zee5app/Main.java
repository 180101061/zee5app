package com.zee.zee5app;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.User;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.service.UserService;
import com.zee.zee5app.service.UserServiceImpl;

public class Main {

	public static void main(String[] args) {
		UserService userService = UserServiceImpl.getInstance();
//		try {
//			userService.insertuser(
//					new User("Rahul","kumar","rahu@gmail.com",LocalDate.now(),LocalDate.of(1999, 12, 29)));
//		} catch (UnableToGenerateIdException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
////		
//		try {
//			userService.insertuser(
//					new User("abhi","chivate","abhi@gmail.com",LocalDate.now(),LocalDate.of(1988, 12, 9)));
//		} catch (UnableToGenerateIdException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		try {
//			userService.insertuser(
//					new User("kaka","kumar","kaka@gmail.com",LocalDate.now(),LocalDate.of(2008, 12, 29)));
//		} catch (UnableToGenerateIdException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		Optional<User> result = userService.getUserByUserId("RK3");
		//System.out.println(result);
		if(!result.isPresent()) {
			System.out.println("No Record Found");
		}
		else {
			User user = result.get();
			System.out.println(user);
		}
		System.out.println("");
		Optional<List<User>> result1 = userService.getAllUser();
		if(!result1.isPresent()) {
			System.out.println("No Record Found");
		}
		else {
			List<User> users = result1.get();
			for(User user : users)System.out.println(user);
		}
//		
		try {
		 String update = userService.updateUser("RK8", new User("R","kumar","rahu@gmail.com",LocalDate.now(),LocalDate.of(1999, 12, 29)));
		 System.out.println(update);
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println( userService.getAllUser());
		
//		
		try {
			userService.deleteUser("kk10");
		} catch (NoDataFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("");
		
		Optional<List<User>> result6 = userService.getAllUser();
		if(!result6.isPresent()) {
			System.out.println("No Record Found");
		}
		else {
			List<User> users = result6.get();
			for(User user : users)System.out.println(user);
		}
	}
}
