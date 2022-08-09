package com.zee.zee5app.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.repo.MovieRepository;
import com.zee.zee5app.repo.MovieRepositoryImpl;
import com.zee.zee5app.repo.UserRepo;
import com.zee.zee5app.repo.UserRepoImpl;


public class MovieServiceImpl implements MovieService {
	
	
	private MovieServiceImpl() {

    }

    private static MovieService movieService;

    public static MovieService getInstance () {
        if(movieService == null) {
            movieService = new MovieServiceImpl();
        }
        return movieService;
    }


    private MovieRepository repo = MovieRepositoryImpl.getInstance();
	@Override
	public Movie insertMovie(Movie movie)  {
		// TODO Auto-generated method stub
		File file = new File(movie.getTrailer1());
		System.out.println(file.getName());
		BufferedInputStream bufferedInputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		try {
			if(movie.getTrailer1()==null||
					movie.getTrailer1()==""||
					!file.exists()) {
				throw new FileNotFoundException("file does not exist");
			}else {
				//
				bufferedInputStream = new BufferedInputStream(
						new FileInputStream(file));
				bufferedOutputStream =new BufferedOutputStream(new FileOutputStream("D:\\Zee5App\\Trailer\\"+file.getName()));
				bufferedOutputStream.write(bufferedInputStream.readAllBytes());
				
				System.out.println("file exists");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				bufferedInputStream.close();
				bufferedOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//trailer file exist or not 
		//shift that file to zee5app/trailer folder
		//then take the path and store it in db. --> halndled by Repo.
		return repo.insertMovie(movie);
	}

	
	
	@Override
	public Movie updateMovie(String movieId, Movie movie) {
		// TODO Auto-generated method stub
		return repo.updateMovie(movieId, movie);
	}

	@Override
	public Optional<Movie> getMovieByMovieId(String movieId) {
		// TODO Auto-generated method stub
		return repo.getMovieByMovieId(movieId);
	}

	@Override
	public Optional<List<Movie>> getAllMovies() {
		// TODO Auto-generated method stub
		return repo.getAllMovies();
	}

	@Override
	public List<Movie> getAllMoviesByGenre(Genres genre) {
		// TODO Auto-generated method stub
		return repo.getAllMoviesByGenre(genre);
	}

	@Override
	public Movie[] getAllMoviesByName(String movieName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteMovieByMovieId(String movieId) throws NoDataFoundException {
		// TODO Auto-generated method stub
		return repo.deleteMovieByMovieId(movieId);
	}
	public List<Movie> findByOrderByMovieNameDsc(){
		return repo.findByOrderByMovieNameDsc();
	}

}
