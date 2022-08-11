package com.zee.zee5app.dto;

import javax.naming.InvalidNameException;

import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.enums.Languages;
import com.zee.zee5app.exceptions.InvalidIdException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//@Setter
@Getter
@EqualsAndHashCode
@ToString
public class WebSeries {
	
	public void setWebSeriesId(String webSeriesId) throws InvalidIdException {
		int len = webSeriesId.length();
		if(len<3) {
			throw new InvalidIdException("Id should contain atleast 3 character");
		}
		this.webSeriesId = webSeriesId;
	}
	public void setActors(String[] actors) {
		this.actors = actors;
	}
	public void setWebSeriesName(String webSeriesName) {
		this.webSeriesName = webSeriesName;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public void setGenre(Genres genre) {
		this.genre = genre;
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
	}
	public void setEpisodeNumber(Integer episodeNumber) {
		this.episodeNumber = episodeNumber;
	}
	public void setTrailer1(String trailer1) {
		this.trailer1 = trailer1;
	}
	
	public WebSeries(String webSeriesId, String[] actors, String webSeriesName, String director, Genres genre,
			String production, String[] languages, Integer episodeNumber, String trailer1) throws InvalidIdException {
		super();
		this.setWebSeriesId(webSeriesId);;
		this.actors = actors;
		this.webSeriesName = webSeriesName;
		this.director = director;
		this.genre = genre;
		this.production = production;
		this.languages = languages;
		this.episodeNumber = episodeNumber;
		this.trailer1 = trailer1;
	}

	public WebSeries() {
		// TODO Auto-generated constructor stub
	}

	private String webSeriesId;
	private String actors[];
    private String webSeriesName;
    private String director;
    private Genres genre;
    private String production;
    private String languages[];
    private Integer episodeNumber;
    private String trailer1;
}
