package com.zee.zee5app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zee.zee5app.config.Config;
import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.enums.Languages;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.service.MovieService;

public class Main2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ApplicationContext applicationContext = new 
				AnnotationConfigApplicationContext(Config.class);
		MovieService movieService = applicationContext.getBean(MovieService.class);
		String[] actor = {"a","b","c"};
		String[] actor1 = {"d","e","f"};
		String[] language1 = {Languages.ENGLISH.name(),Languages.HINDI.name()};
		String[] language = {Languages.KANNADA.name(),Languages.TAMIL.name(),Languages.TELUGU.name(),Languages.HINDI.name()};
		try {
			movieService.insertMovie(new Movie("VKR001", actor, "Vikrant Rona", "abc", Genres.ACTION, "abc", language, 2.2f,"C:\\Users\\rahul.kumar1\\Downloads\\OnePlus.mp4"));
			movieService.insertMovie(new Movie("TSR01", actor1, "The Shawshank Redemption", "nhi pata", Genres.SUSPENSE, "Pata nhi", language, 2.9f,"C:\\Users\\rahul.kumar1\\Downloads\\OnePlus.mp4"));
			movieService.insertMovie(new Movie("Rk001", actor, "Bahubali", "abc", Genres.ACTION, "abc", language, 2.2f,"C:\\Users\\rahul.kumar1\\Downloads\\OnePlus.mp4"));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Get all movies
		Optional<List<Movie>> result = movieService.getAllMovies();
		if(!result.isPresent()) {
			System.out.println("No Record Found");
		}
		else {
			List<Movie> movies6 = result.get();
			for(Movie movie : movies6)System.out.println(movie);
		}
		System.out.println("");
		//get movie by movie id 
		Optional<Movie> result1 = movieService.getMovieByMovieId("TSR01");
		if(!result1.isPresent()) {
			System.out.println("No Record Found");
		}
		else {
			Movie movie = result1.get();
			System.out.println("movie id TSR01 : "+ movie);
		}
		//update
		try {
			movieService.updateMovie("TSR01", new Movie("TSR01", actor1, "The Shawshank Redemption", "nhi pata", Genres.SUSPENSE, "Pata nhi", language1, 2.0f,"C:\\Users\\rahul.kumar1\\Downloads\\OnePlus.mp4"));
		} catch (InvalidNameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidIdException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//get movies by genre
		List<Movie> movies2 = movieService.getAllMoviesByGenre(Genres.ACTION);
		for(Movie movie : movies2) {
			System.out.println("Movies by Genres Action: "+movie);
			
		}
		 movies2 = movieService.getAllMoviesByGenre(Genres.SUSPENSE);
		for(Movie movie : movies2) {
			System.out.println("Movies by Genres SUSPENSE: "+movie);
			
		}
		//delete 
		try {
			movieService.deleteMovieByMovieId("RK001");
		} catch (NoDataFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Optional<List<Movie>> result3 = movieService.getAllMovies();
		if(!result3.isPresent()) {
			System.out.println("No Record Found");
		}
		else {
			List<Movie> movies6 = result3.get();
			for(Movie movie : movies6)System.out.println(movie);
		}
	}

}
