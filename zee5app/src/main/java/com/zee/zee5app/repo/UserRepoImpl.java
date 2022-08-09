package com.zee.zee5app.repo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.User;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.utils.DBUtils;

public class UserRepoImpl implements UserRepo {
	private UserRepoImpl() {

	}

	private static UserRepoImpl userRepoImpl;

	public static UserRepoImpl getInstance() {
		if (userRepoImpl == null) {
			userRepoImpl = new UserRepoImpl();
		}
		return userRepoImpl;
	}
	
	private DBUtils dbUtils = DBUtils.getInstance();
	
	@Override
	public User insertUser(User user) throws UnableToGenerateIdException {
		// TODO Auto-generated method stub
		Connection connection  = null;
		PreparedStatement preparedStatement = null;
		String insertStatement  = "insert into user_table "
				+ "(userid,firstname,lastname,email,doj,dob,active) "
				+ "values(?,?,?,?,?,?,?)";
		//connection object 
		connection = dbUtils.getConnection();
		
		//statement object (Prepared)
		try {
			preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1, dbUtils.userIdGenerator(user.getFirstName(), user.getLastName()));
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getEmail());
			//preparedStatement.setDate(5,new Date(user.getDoj().toEpochDay()));
			preparedStatement.setDate(5,Date.valueOf(user.getDoj()));
			preparedStatement.setDate(6, Date.valueOf(user.getDob()));
			preparedStatement.setBoolean(7, user.isActive());
			int result = preparedStatement.executeUpdate();
			if(result>0) {
				System.out.println("Success");
				return user;
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
	public String updateUser(String userId, User user) throws InvalidIdException {
		// TODO Auto-generated method stub
		Connection connection  = null;
		PreparedStatement preparedStatement = null;
		String query  = "select * from user_table where userid=?";
		ResultSet resultSet = null;
		//connection object 
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				//record exists
				//Inside the resultSet
//				//user object from resultSet data.
//				User user1 = new User();
//				user1.setUserId(userId);
//				user1.setFirstName(user.getFirstName());
//				user1.setLastName(user.getLastName());
//				user1.setEmail(user.getEmail());
//				user1.setDoj(user.getDoj());
//				user1.setDob(user.getDob());
//				user1.setActive(user.isActive());
				//System.out.println(user);
				try {
					String result = deleteUserById(userId);
				} catch (NoDataFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					insertUser(user);
				} catch (UnableToGenerateIdException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "success"; 
			}else {
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
	public String deleteUserById(String userId) throws NoDataFoundException  {
		// TODO Auto-generated method stub
		String deleteStatement = "delete from user_table where userid=?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setString(1, userId);
			int result = preparedStatement.executeUpdate();
			if (result > 0) {
				// System.out.println("Success");
				return "Successfully deleted user" + userId;
			} else {
				throw new NoDataFoundException("userid " + userId + " not found");
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbUtils.closeConnection(connection);
		}
		//We should get the result based on that we will return the result;
		
		return null;
		
	}

	@Override
	public Optional<List<User>> getAllUsers() {
		Connection connection  = null;
		PreparedStatement preparedStatement = null;
		String query  = "select * from user_table";
		ResultSet resultSet = null;
		List<User> users = new ArrayList<>();
		//connection object 
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			//preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				//record exists
				//Inside the resultSet
				//user object from resultSet data.
				User user = new User();
				user.setUserId(resultSet.getString("userid"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
				user.setEmail(resultSet.getString("email"));
				user.setDoj(resultSet.getDate("doj").toLocalDate());
				user.setDob(resultSet.getDate("dob").toLocalDate());
				user.setActive(resultSet.getBoolean("active"));
				//System.out.println(user);
				users.add(user);
				 
			}
			if(!users.isEmpty()){
				return Optional.of(users);
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
	public Optional<User> getUserById(String userId) {
		// TODO Auto-generated method stub
		Connection connection  = null;
		PreparedStatement preparedStatement = null;
		String query  = "select * from user_table where userid=?";
		ResultSet resultSet = null;
		//connection object 
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				//record exists
				//Inside the resultSet
				//user object from resultSet data.
				User user = new User();
				user.setUserId(userId);
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
				user.setEmail(resultSet.getString("email"));
				user.setDoj(resultSet.getDate("doj").toLocalDate());
				user.setDob(resultSet.getDate("dob").toLocalDate());
				user.setActive(resultSet.getBoolean("active"));
				//System.out.println(user);
				return Optional.of(user); 
			}else {
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

}
