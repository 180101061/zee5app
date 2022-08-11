package com.zee.zee5app.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.WebSeries;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.enums.Languages;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.utils.DBUtils;

@Repository
public class WebSeriesRepoImpl implements WebSeriesRepo {

//	private WebSeriesRepoImpl() {
//		// TODO Auto-generated constructor stub
//	}
//
//	private static WebSeriesRepoImpl seriesRepoImpl;
//
//	public static WebSeriesRepoImpl getInstance() {
//		if (seriesRepoImpl == null) {
//			seriesRepoImpl = new WebSeriesRepoImpl();
//		}
//		return seriesRepoImpl;
//	}
	@Autowired
	DataSource dataSource;

	@Override
	public WebSeries insertSeries(WebSeries webSeries) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertStatement = "insert into series_table "
				+ "(seriesid,actors,seriesname,director,genre,production,languages,numberofepisodes,trailer) "
				+ "values(?,?,?,?,?,?,?,?,?)";
		// connection object

		// statement object (Prepared)
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1, webSeries.getWebSeriesId());
			preparedStatement.setString(2, String.join(",", webSeries.getActors()));
			preparedStatement.setString(3, webSeries.getWebSeriesName());
			preparedStatement.setString(4, webSeries.getDirector());
			preparedStatement.setString(5, webSeries.getGenre().toString());
			// preparedStatement.setDate(5,new Date(user.getDoj().toEpochDay()));
			preparedStatement.setString(6, webSeries.getProduction());
			// System.out.println(movie.getLanguages());
			preparedStatement.setString(7, String.join(",", webSeries.getLanguages()));
			preparedStatement.setFloat(8, webSeries.getEpisodeNumber());
			preparedStatement.setString(9, webSeries.getTrailer1());
			int result = preparedStatement.executeUpdate();
			if (result > 0) {
				System.out.println("Successfully inserted !");
				return webSeries;
			} else {
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// We should get the result based on that we will return the result;

		return null;

	}

	@Override
	public String updateMovie(String webSeriesId, WebSeries webSeries) throws InvalidIdException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		// seriesid,actors,seriesname,director,genre,production,languages,numberofepisodes,trailer
		String updateQuery = "UPDATE series_table SET actors=?,seriesname=?,director=?,genre=?,production=?,languages=?,numberofepisodes=?,trailer=? WHERE seriesid=?";
		// ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setString(1, String.join(",", webSeries.getActors()));
			preparedStatement.setString(2, webSeries.getWebSeriesName());
			preparedStatement.setString(3, webSeries.getDirector());
			preparedStatement.setString(4, webSeries.getGenre().toString());
			preparedStatement.setString(5, webSeries.getProduction());

			preparedStatement.setString(6, String.join(",", webSeries.getLanguages()));
			preparedStatement.setFloat(7, webSeries.getEpisodeNumber());
			preparedStatement.setString(8, webSeries.getTrailer1());
			preparedStatement.setString(9, webSeriesId);
			int result = 0;
			result = preparedStatement.executeUpdate();
			if (result > 0) {
				System.out.println("Updated Successfully ! ");
				return "Updated Successfully ";
			} else {
				throw new InvalidIdException("Id not found");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public Optional<WebSeries> getSereisByWebSeriesId(String webSeriesId) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "select * from series_table where seriesid=?";
		ResultSet resultSet = null;
		// connection object

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, webSeriesId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				WebSeries series = new WebSeries();

				try {
					series.setWebSeriesId(resultSet.getString("seriesid"));
				} catch (InvalidIdException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				series.setActors(resultSet.getString("actors").split(","));

				series.setWebSeriesName(resultSet.getString("seriesname"));

				series.setDirector(resultSet.getString("director"));
				series.setGenre(Genres.valueOf(resultSet.getString("genre")));
				series.setProduction(resultSet.getString("production"));

				String[] lang = resultSet.getString("languages").split(",");
				String[] language = new String[lang.length];
				for (int i = 0; i < lang.length; i++) {
					language[i] = Languages.valueOf(lang[i]).name();
				}

				try {
					series.setLanguages(language);
				} catch (InvalidNameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				series.setEpisodeNumber(resultSet.getInt("numberofepisodes"));
				series.setTrailer1(resultSet.getString("trailer"));
				return Optional.of(series);
			} else {
				return Optional.empty();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public Optional<List<WebSeries>> getAllSereis() {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "select * from series_table";
		ResultSet resultSet = null;
		List<WebSeries> allSeries = new ArrayList<>();

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				WebSeries series = new WebSeries();
				try {
					series.setWebSeriesId(resultSet.getString("seriesid"));
				} catch (InvalidIdException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				series.setActors(resultSet.getString("actors").split(","));

				series.setWebSeriesName(resultSet.getString("seriesname"));

				series.setDirector(resultSet.getString("director"));
				series.setGenre(Genres.valueOf(resultSet.getString("genre")));
				series.setProduction(resultSet.getString("production"));

				String[] lang = resultSet.getString("languages").split(",");
				String[] language = new String[lang.length];
				for (int i = 0; i < lang.length; i++) {
					language[i] = Languages.valueOf(lang[i]).name();
				}

				try {
					series.setLanguages(language);
				} catch (InvalidNameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				series.setEpisodeNumber(resultSet.getInt("numberofepisodes"));
				series.setTrailer1(resultSet.getString("trailer"));

				// System.out.println(user);
				allSeries.add(series);

			}
			if (!allSeries.isEmpty()) {
				return Optional.of(allSeries);
			} else {
				return Optional.empty();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<WebSeries> getAllSeriesByGenre(Genres genre) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "select * from series_table where genre=?";
		ResultSet resultSet = null;
		List<WebSeries> allSeries = new ArrayList<>();

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, genre.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				WebSeries series = new WebSeries();
				try {
					series.setWebSeriesId(resultSet.getString("seriesid"));
				} catch (InvalidIdException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				series.setActors(resultSet.getString("actors").split(","));

				series.setWebSeriesName(resultSet.getString("seriesname"));

				series.setDirector(resultSet.getString("director"));
				series.setGenre(Genres.valueOf(resultSet.getString("genre")));
				series.setProduction(resultSet.getString("production"));

				String[] lang = resultSet.getString("languages").split(",");
				String[] language = new String[lang.length];
				for (int i = 0; i < lang.length; i++) {
					language[i] = Languages.valueOf(lang[i]).name();
				}
				try {
					series.setLanguages(language);
				} catch (InvalidNameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				series.setEpisodeNumber(resultSet.getInt("numberofepisodes"));

				series.setTrailer1(resultSet.getString("trailer"));

				// System.out.println(user);
				allSeries.add(series);

			}
			if (!allSeries.isEmpty()) {
				return allSeries;
			} else {
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public WebSeries[] getAllSeriesByName(String webSeriesName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteSeriesBySeriesId(String webSeriesId) throws NoDataFoundException {
		// TODO Auto-generated method stub
		String deleteStatement = "delete from series_table where seriesid=?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setString(1, webSeriesId);
			int result = preparedStatement.executeUpdate();
			if(result>0) {
				return "Succesfully deleted seriesid "+webSeriesId; 
			}else {
				throw new NoDataFoundException("seriesId "+webSeriesId+" not found!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
