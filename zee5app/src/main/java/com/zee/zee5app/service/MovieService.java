package com.zee.zee5app.service;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.exceptions.NoDataFoundException;

public interface MovieService {
    
    public Movie insertMovie(Movie movie) throws FileNotFoundException, IOException;
    public Movie updateMovie(String movieId, Movie movie);
    public List<Movie> findByOrderByMovieNameDsc();
    public Optional<Movie> getMovieByMovieId(String movieId);
    public Optional<List<Movie>> getAllMovies();
    public List<Movie> getAllMoviesByGenre(Genres genre);
    public Movie[] getAllMoviesByName(String movieName);
    public String deleteMovieByMovieId(String movieId) throws NoDataFoundException;
}

