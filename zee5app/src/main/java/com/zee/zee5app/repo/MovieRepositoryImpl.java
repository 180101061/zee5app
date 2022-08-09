package com.zee.zee5app.repo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.User;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.enums.Languages;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.utils.DBUtils;

public class MovieRepositoryImpl implements MovieRepository {

	private MovieRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}

	private static MovieRepositoryImpl movieRepoImpl;

	public static MovieRepositoryImpl getInstance() {
		if (movieRepoImpl == null) {
			movieRepoImpl = new MovieRepositoryImpl();
		}
		return movieRepoImpl;
	}
	private DBUtils dbUtils = DBUtils.getInstance();
	@Override
	public Movie insertMovie(Movie movie) {
		// TODO Auto-generated method stub
		Connection connection  = null;
		PreparedStatement preparedStatement = null;
		String insertStatement  = "insert into movie_table "
				+ "(movieid,actors,moviename,director,genre,production,languages,movielength,trailer) "
				+ "values(?,?,?,?,?,?,?,?,?)";
		//connection object 
		connection = dbUtils.getConnection();
		
		//statement object (Prepared)
		try {
			preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1, movie.getMovieId());
			preparedStatement.setString(2,String.join(",", movie.getActors()));
			preparedStatement.setString(3, movie.getMovieName());
			preparedStatement.setString(4, movie.getDirector());
			preparedStatement.setString(5, movie.getGenre().toString());
			//preparedStatement.setDate(5,new Date(user.getDoj().toEpochDay()));
			preparedStatement.setString(6,movie.getProduction());
			//System.out.println(movie.getLanguages());
			preparedStatement.setString(7,String.join(",", movie.getLanguages()));
			preparedStatement.setFloat(8, movie.getMovieLength());
			preparedStatement.setString(9, movie.getTrailer1());
			int result = preparedStatement.executeUpdate();
			if(result>0) {
				System.out.println("Success");
				return movie;
			}else {
				return null;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbUtils.closeConnection(connection);
		}
		//We should get the result based on that we will return the result;
		
		return null;
	}

	@Override
	public Movie updateMovie(String movieId, Movie movie) {
		// TODO Auto-generated method stub
		Connection connection  = null;
		PreparedStatement preparedStatement = null;
		String query  = "select * from movie_table where movieid=?";
		ResultSet resultSet = null;
		//connection object 
		connection = dbUtils.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, movieId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
//				Movie movie1 = new Movie();
//				try {
//					movie1.setMovieId(movie.getMovieId());
//				} catch (InvalidIdException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				movie1.setActors(movie.getActors());
//				try {
//					movie1.setMovieName(movie.getMovieName());
//				} catch (InvalidNameException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				movie1.setDirector(movie.getDirector());
//				movie1.setGenre(movie.getGenre());
//				movie1.setProduction(movie.getProduction());
//
//				
//				try {
//					movie1.setLanguages(movie.getLanguages());
//				} catch (InvalidNameException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				movie1.setMovieLength(movie.getMovieLength());
//				movie1.setTrailer1(movie.getTrailer1());
				try {
					deleteMovieByMovieId(movieId);
				} catch (NoDataFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				insertMovie(movie);
				System.out.println("Succefully Updated");
				return movie;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbUtils.closeConnection(connection);
		}
		
		return null;
	}

	@Override
	public List<Movie> findByOrderByMovieNameDsc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Movie> getMovieByMovieId(String movieId) {
		// TODO Auto-generated method stub
		Connection connection  = null;
		PreparedStatement preparedStatement = null;
		String query  = "select * from movie_table where movieid=?";
		ResultSet resultSet = null;
		//connection object 
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, movieId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				Movie movie = new Movie();
				try {
					movie.setMovieId(resultSet.getString("movieid"));
				} catch (InvalidIdException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				movie.setActors(resultSet.getString("actors").split(","));
				try {
					movie.setMovieName(resultSet.getString("moviename"));
				} catch (InvalidNameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				movie.setDirector(resultSet.getString("director"));
				movie.setGenre(Genres.valueOf(resultSet.getString("genre")));
				movie.setProduction(resultSet.getString("production"));
				
				String[] lang = resultSet.getString("languages").split(",");
				String[] language = new String[lang.length];
				for(int i=0;i<lang.length;i++) {
					language[i] = Languages.valueOf(lang[i]).name();
				}
				try {
					movie.setLanguages(language);
				} catch (InvalidNameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				movie.setMovieLength(resultSet.getFloat("movielength"));
				movie.setTrailer1(resultSet.getString("trailer"));
				return Optional.of(movie);
			}else {
				return Optional.empty();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbUtils.closeConnection(connection);
		}
		return Optional.empty();
	}

	@Override
	public Optional<List<Movie>> getAllMovies() {
		Connection connection  = null;
		PreparedStatement preparedStatement = null;
		String query  = "select * from movie_table";
		ResultSet resultSet = null;
		List<Movie> movies = new ArrayList<>();
		//connection object 
		//(movieid,actors,moviename,director,genre,production,languages,movielength,trailer)
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			//preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				//record exists
				//Inside the resultSet
				//user object from resultSet data.
				Movie movie = new Movie();
				try {
					movie.setMovieId(resultSet.getString("movieid"));
				} catch (InvalidIdException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				movie.setActors(resultSet.getString("actors").split(","));
				try {
					movie.setMovieName(resultSet.getString("moviename"));
				} catch (InvalidNameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				movie.setDirector(resultSet.getString("director"));
				movie.setGenre(Genres.valueOf(resultSet.getString("genre")));
				movie.setProduction(resultSet.getString("production"));
				
				String[] lang = resultSet.getString("languages").split(",");
				String[] language = new String[lang.length];
				for(int i=0;i<lang.length;i++) {
					language[i] = Languages.valueOf(lang[i]).name();
				}
				try {
					movie.setLanguages(language);
				} catch (InvalidNameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				movie.setMovieLength(resultSet.getFloat("movielength"));
				movie.setTrailer1(resultSet.getString("trailer"));
				
				//System.out.println(user);
				movies.add(movie);
				 
			}
			if(!movies.isEmpty()){
				return Optional.of(movies);
			}
			else {
				return Optional.empty();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbUtils.closeConnection(connection);
		}
		return null;
	}
	@Override
	public List<Movie> getAllMoviesByGenre(Genres genre) {
		// TODO Auto-generated method stub
		Connection connection  = null;
		PreparedStatement preparedStatement = null;
		String query  = "select * from movie_table where genre=?";
		ResultSet resultSet = null;
		List<Movie> movies = new ArrayList<>();
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, genre.toString());
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				//record exists
				//Inside the resultSet
				//user object from resultSet data.
				Movie movie = new Movie();
				try {
					movie.setMovieId(resultSet.getString("movieid"));
				} catch (InvalidIdException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				movie.setActors(resultSet.getString("actors").split(","));
				try {
					movie.setMovieName(resultSet.getString("moviename"));
				} catch (InvalidNameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				movie.setDirector(resultSet.getString("director"));
				movie.setGenre(Genres.valueOf(resultSet.getString("genre")));
				movie.setProduction(resultSet.getString("production"));
				
				String[] lang = resultSet.getString("languages").split(",");
				String[] language = new String[lang.length];
				for(int i=0;i<lang.length;i++) {
					language[i] = Languages.valueOf(lang[i]).name();
				}
				try {
					movie.setLanguages(language);
				} catch (InvalidNameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				movie.setMovieLength(resultSet.getFloat("movielength"));
				movie.setTrailer1(resultSet.getString("trailer"));
				
				//System.out.println(user);
				movies.add(movie);
				 
			}
			if(!movies.isEmpty()){
				return movies;
			}
			else {
				return null;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbUtils.closeConnection(connection);
		}
		return null;
	}

	@Override
	public Movie[] getAllMoviesByName(String movieName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteMovieByMovieId(String movieId) throws  NoDataFoundException {
		// TODO Auto-generated method stub
		String deleteStatement = "delete from movie_table where movieid=?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setString(1, movieId);
			int result = preparedStatement.executeUpdate();
			if(result>0) {
				return "Succesfully deleted movieid "+movieId; 
			}else {
				throw new NoDataFoundException("MovieId "+movieId+" not found!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbUtils.closeConnection(connection);
		}
		
		return null;
	}

}
