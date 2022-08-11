package com.zee.zee5app;

import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zee.zee5app.config.Config;
import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.WebSeries;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.enums.Languages;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.repo.WebSeriesRepo;
import com.zee.zee5app.service.MovieService;
import com.zee.zee5app.service.WebSeriesService;

public class Main3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ApplicationContext applicationContext = new 
				AnnotationConfigApplicationContext(Config.class);
		//MovieService movieService = applicationContext.getBean(MovieService.class);
		WebSeriesService webSeriesService = applicationContext.getBean(WebSeriesService.class);
		String[] actor = {"a","b","c"};
		String[] actor1 = {"d","e","f"};
		String[] language1 = {Languages.ENGLISH.name(),Languages.HINDI.name()};
		String[] language = {Languages.KANNADA.name(),Languages.TAMIL.name(),Languages.TELUGU.name(),Languages.HINDI.name()};

//		try {
//			webSeriesService.insertSeries(new WebSeries("TFM01", actor, "The Family Man", "abc", Genres.SUSPENSE, "abc", language, 8,"C:\\Users\\rahul.kumar1\\Downloads\\OnePlus.mp4"));
//		webSeriesService.insertSeries(new WebSeries("MZ02", actor, "Mirzapur", "abc", Genres.ACTION, "abc", language, 6,"C:\\Users\\rahul.kumar1\\Downloads\\OnePlus.mp4"));

//		} catch (InvalidIdException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			webSeriesService.updateMovie("TFM01",new WebSeries("TFM01", actor1, "The Family Man", "abc", Genres.SUSPENSE, "abc", language1, 8,"C:\\Users\\rahul.kumar1\\Downloads\\OnePlus.mp4") );
//		} catch (InvalidIdException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Optional<WebSeries> result = webSeriesService.getSereisByWebSeriesId("TFM01");
//		if(!result.isPresent()) {
//			System.out.println("No Record Found");
//		}
//		else {
//			WebSeries series = result.get();
//			System.out.println("movie id TFM01 : "+ series);
//		}
//		System.out.println("");
//		Optional<List<WebSeries>> result2 = webSeriesService.getAllSereis();
//		if(!result2.isPresent()) {
//			System.out.println("No Record Found");
//		}
//		else {
//			List<WebSeries> allSeries = result2.get();
//			for(WebSeries series : allSeries)System.out.println(series);
//		}
//		List<WebSeries> allSeries = webSeriesService.getAllSeriesByGenre(Genres.ACTION);
//		for(WebSeries series : allSeries)System.out.println("ACTION  :  "+series);
		
		try {
			System.out.println(webSeriesService.deleteSeriesBySeriesId("MZ02"));
		} catch (NoDataFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
