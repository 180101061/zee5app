package com.zee.zee5app.dto;

import javax.naming.InvalidNameException;

import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.enums.Languages;
import com.zee.zee5app.exceptions.InvalidIdException;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Movie {
	
	
    private String movieId;
    public void setMovieId(String movieId) throws InvalidIdException {
		//movie id is of length 5 and 7
    	int length = movieId.length();
    	if(length>=5&&length<=7) {
    		this.movieId = movieId;
    	}
    	else {
    		throw new InvalidIdException("Invalid Movie Id");
    	}
	}
    
	public void setActors(String[] actors) {
		this.actors = actors;
	}

	public void setMovieName(String movieName) throws InvalidNameException {
		int length = movieName.length();
		if(movieName==null||movieName == ""||length<3) {
			throw new InvalidNameException("Invalid Movie Name");
		}
		else this.movieName = movieName;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	

	public void setProduction(String production) {
		this.production = production;
	}

	public void setLanguages(String[] languages) throws InvalidNameException {
		int count=0;
		for(String string: languages) {
			//System.out.println(Languages.valueOf(string));
			for(Languages l : Languages.values()) {
				if(Languages.valueOf(string).compareTo(l)==0) {
					count++;
				}
			}
		}
		if(count!=languages.length) {
            throw new InvalidNameException("Invalid language set");
        }
		this.languages = languages;
		//throw Exception()
	}

	public void setMovieLength(float movieLength) {
		
		this.movieLength = movieLength;
	}
	public Movie() {
		// TODO Auto-generated constructor stub
	}
    public Movie(String movieId, String[] actors, String movieName, String director, Genres genre, String production,
			String[] languages, float movieLength,String trailer1) throws InvalidIdException, InvalidNameException {
		super();
		this.setMovieId(movieId);
		this.actors = actors;
		this.setMovieName(movieName);
		this.director = director;
		this.genre = genre;
		this.production = production;
		this.setLanguages(languages);
		this.movieLength = movieLength;
		this.trailer1 = trailer1;
	}
	public void setGenre(Genres genre) {
		this.genre = genre;
	}
	private String actors[];
    private String movieName;
    private String director;
    private Genres genre;
    private String production;
    private String languages[];
    private float movieLength;
    private String trailer1;
    //private byte[] trailer2;
}

