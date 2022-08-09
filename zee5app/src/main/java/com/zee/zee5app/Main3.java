

package com.zee.zee5app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.User;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.enums.Languages;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.service.MovieService;
import com.zee.zee5app.service.MovieServiceImpl;
import com.zee.zee5app.service.UserService;
import com.zee.zee5app.service.UserServiceImpl;

public class Main3 {
	
	
	
	
	public static void main(String[] args) {
		MovieService movieService = MovieServiceImpl.getInstance();
		String[] actor = {"a","b","c"};
		String[] actor1 = {"d","e","f"};
		String[] language1 = {Languages.ENGLISH.name(),Languages.HINDI.name()};
		String[] language = {Languages.KANNADA.name(),Languages.TAMIL.name(),Languages.TELUGU.name(),Languages.HINDI.name()};
		
//		public Movie(String movieId, String[] actors, String movieName, String director, Genres genre, String production,
//				String[] languages, float movieLength)
			
			try {
				movieService.insertMovie(new Movie("VKR001", actor, "Vikrant Rona", "abc", Genres.ACTION, "abc", language, 2.2f,"C:\\Users\\rahul.kumar1\\Downloads\\OnePlus.mp4"));
				//movieService.insertMovie(new Movie("TSR01", actor1, "The Shawshank Redemption", "nhi pata", Genres.SUSPENSE, "Pata nhi", language1, 2.9f,"C:\\Users\\rahul.kumar1\\Downloads\\OnePlus.mp4"));
				movieService.insertMovie(new Movie("Rk001", actor, "Bahubali", "abc", Genres.ACTION, "abc", language, 2.2f,"C:\\Users\\rahul.kumar1\\Downloads\\OnePlus.mp4"));

			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (InvalidNameException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (InvalidIdException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
//			List<Movie> movies = movieService.findByOrderByMovieNameDsc();
//			for(Movie movie : movies)System.out.println(movie);
 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		//get movie by movie id 
//		Optional<Movie> result = movieService.getMovieByMovieId("TSR01");
////		//System.out.println(result);
//		if(!result.isPresent()) {
//			System.out.println("No Record Found");
//		}
//		else {
//			Movie movie = result.get();
//			System.out.println(movie);
//		}
//		//update
//		try {
//			movieService.updateMovie("TSR01", new Movie("TSR01", actor1, "The Shawshank Redemption", "nhi pata", Genres.SUSPENSE, "Pata nhi", language1, 2.0f,"TSR trailer"));
//		} catch (InvalidNameException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (InvalidIdException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		//get movies by genre
//		List<Movie> movies = movieService.getAllMoviesByGenre(Genres.ACTION);
//		for(Movie movie : movies) {
//			System.out.println("Movies by Genres Action: "+movie);
//			
//		}
//		 movies = movieService.getAllMoviesByGenre(Genres.SUSPENSE);
//		for(Movie movie : movies) {
//			System.out.println("Movies by Genres SUSPENSE: "+movie);
//			
//		}
//		//delete 
//		try {
//			movieService.deleteMovieByMovieId("RK001");
//		} catch (NoDataFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Optional<List<Movie>> result1 = movieService.getAllMovies();
//		if(!result1.isPresent()) {
//			System.out.println("No Record Found");
//		}
//		else {
//			List<Movie> movies6 = result1.get();
//			for(Movie movie : movies6)System.out.println(movie);
//		}
	}

}
