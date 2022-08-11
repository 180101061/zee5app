package com.zee.zee5app.repo;

import java.util.List;
import java.util.Optional;


import com.zee.zee5app.dto.WebSeries;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.NoDataFoundException;


public interface WebSeriesRepo {

	public WebSeries insertSeries(WebSeries webSeries);
    public String updateMovie(String webSeriesId, WebSeries webSereis) throws InvalidIdException;
    public Optional<WebSeries> getSereisByWebSeriesId(String webSeriesId);
    public Optional<List<WebSeries>> getAllSereis();
    public List<WebSeries> getAllSeriesByGenre(Genres genre);
    public WebSeries[] getAllSeriesByName(String webSeriesName);
    public String deleteSeriesBySeriesId(String webSeriesId) throws NoDataFoundException ;
	
}
