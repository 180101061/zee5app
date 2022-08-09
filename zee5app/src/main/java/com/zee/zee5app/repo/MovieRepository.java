package com.zee.zee5app.repo;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.NoDataFoundException;

public interface MovieRepository {
    
    public Movie insertMovie(Movie movie);
    public Movie updateMovie(String movieId, Movie movie);
    public List<Movie> findByOrderByMovieNameDsc();
    public Optional<Movie> getMovieByMovieId(String movieId);
    public Optional<List<Movie>> getAllMovies();
    public List<Movie> getAllMoviesByGenre(Genres genre);
    public Movie[] getAllMoviesByName(String movieName);
    public String deleteMovieByMovieId(String movieId) throws NoDataFoundException;
}
