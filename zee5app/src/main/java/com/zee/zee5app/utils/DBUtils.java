package com.zee.zee5app.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.zee.zee5app.exceptions.UnableToGenerateIdException;

public class DBUtils {
//	public static void main(String[] args) {
//		dbUtils = dbUtils = DBUtils.getInstance();
//		Properties properties =  dbUtils.loadProperties();
//		System.out.println(properties);
//	}

	// TODO Auto-generated constructor stub
	private DBUtils() {

	}

	private static DBUtils dbUtils;

	public static DBUtils getInstance() {
		if (dbUtils == null) {
			dbUtils = new DBUtils();
		}
		return dbUtils;
	}

	public  Connection getConnection() {
		//to provide the connection 
		Properties properties = loadProperties();
		
		Connection connection = null;
		try {
			connection  = DriverManager.getConnection(properties.getProperty("db.url"), 
														properties.getProperty("db.username"), 
															properties.getProperty("db.password"));
			return connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private Properties	loadProperties(){
		Properties properties = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = DBUtils.class.getClassLoader()
					.getResourceAsStream("application.properties");
			System.out.println(inputStream!=null);
			properties.load(inputStream);
			return properties;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//properties.load(inputStream);
		return properties;
		
	}
	public String userIdGenerator(String firstName,String lastName)throws UnableToGenerateIdException {
		//it is responsible to generate user userId
		//1st retrieve the value(db stored value from idgen table)
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int id =0;
		String query = "SELECT id from useridgenerator";
		String updateIdStatement = "update useridgenerator set id=?";
		String newId = null;
		int updateResult = 0;
		connection  = this.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				id = resultSet.getInt(1);
				id = ++id;
				newId = firstName.charAt(0)+""+lastName.charAt(0)+""+String.valueOf(id);
				//newId = firstName.charAt(0)+lastName.charAt(0)+""+id;
				System.out.println(newId);
				//then increment the number(id which is retrieved from db)
				//update
				preparedStatement = connection.prepareStatement(updateIdStatement);
				preparedStatement.setInt(1, id);
				updateResult = preparedStatement.executeUpdate();
				if(updateResult==0) {
					throw new UnableToGenerateIdException("unable to generate id");
				}
				return newId;
			}
			// take 1st char from firstNAme and lastName
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UnableToGenerateIdException("unable to generate id"+e.getMessage());
		}finally {
			this.closeConnection(connection);
		}
		return null;
		
		
		//then cancate and return

	}
	public static void main(String[] args) {

		String result="";
		try {
			result = DBUtils.getInstance().userIdGenerator("Rahul", "Kumar");
		} catch (UnableToGenerateIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
	}
}
