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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.WebSeries;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.repo.MovieRepository;
import com.zee.zee5app.repo.WebSeriesRepo;
@Service
public class WebSeriesServiceImpl implements WebSeriesService {

	@Autowired
	private WebSeriesRepo repo;
	@Override
	public WebSeries insertSeries(WebSeries webSeries) {
		// TODO Auto-generated method stub
		File file = new File(webSeries.getTrailer1());
		System.out.println(file.getName());
		BufferedInputStream bufferedInputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		try {
			if(webSeries.getTrailer1()==null||
					webSeries.getTrailer1()==""||
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
		return repo.insertSeries(webSeries);
	}

	@Override
	public String updateMovie(String webSeriesId, WebSeries webSereis) throws InvalidIdException {
		// TODO Auto-generated method stub
		return repo.updateMovie(webSeriesId, webSereis);
		
	}

	@Override
	public Optional<WebSeries> getSereisByWebSeriesId(String webSeriesId) {
		// TODO Auto-generated method stub
		return repo.getSereisByWebSeriesId(webSeriesId);
	}

	@Override
	public Optional<List<WebSeries>> getAllSereis() {
		// TODO Auto-generated method stub
		return repo.getAllSereis();
	}

	@Override
	public List<WebSeries> getAllSeriesByGenre(Genres genre) {
		// TODO Auto-generated method stub
		return repo.getAllSeriesByGenre(genre);
	}

	@Override
	public WebSeries[] getAllSeriesByName(String webSeriesName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteSeriesBySeriesId(String webSeriesId) throws NoDataFoundException {
		// TODO Auto-generated method stub
		return repo.deleteSeriesBySeriesId(webSeriesId);
	}

}
