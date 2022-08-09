package com.zee.zee5app;

import java.io.FileNotFoundException;
import java.sql.Wrapper;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.naming.InvalidNameException;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.enums.Languages;
import com.zee.zee5app.exceptions.InvalidIdException;

public class ExceptionMain {
	
	public static void main(String[] args) {
		TreeMap<Integer, String> hashMap = new TreeMap<>();
		hashMap.put(1, "abhi");
		hashMap.put(3, "Rahul");
		hashMap.put(2, "koi aur");
		hashMap.put(-10, "kuch aur");
		//hashMap.put(null, "I");
		for(Entry<Integer, String> entry : hashMap.entrySet()) {
			System.out.println(entry);
		}
		
		System.out.println(hashMap.get(-10));
	}
	
	
//	public static int methodA() throws FileNotFoundException {
//        throw new FileNotFoundException();
//    }
//    
//    public static int methodB() throws FileNotFoundException {
//        return methodA();
//        
//    }
//    public static void methodC() throws FileNotFoundException {
//        methodB();
//    }
    


//  try {
//  methodC();
//} catch (FileNotFoundException e) {
//  // TODO Auto-generated catch block
//  e.printStackTrace();
//}

//}
//	public static void main(String[] args) {
//		String[] actor = {"a","b","c"};
//		String[] language = {Languages.KANNADA.name(),Languages.TAMIL.name(),Languages.TELUGU.name(),Languages.HINDI.name()};
//		
//			try {
//				Movie movie = new Movie("VKR001", actor, "Vikrant Rona", "abc", Genres.ACTION, "abc", language, 2.2f);
//				Movie movie2 = new Movie("VKR001", actor, "Vikrant Rona", "abc", Genres.ACTION, "abc", language, 2.2f);
//				System.out.println(movie.hashCode());
//				System.out.println(movie2.hashCode());
//				System.out.println(movie.equals(movie2));
//			} catch (InvalidNameException | InvalidIdException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		
//	}
}
