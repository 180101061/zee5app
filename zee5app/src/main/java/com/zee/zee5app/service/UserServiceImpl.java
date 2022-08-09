package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.User;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.repo.UserRepo;
import com.zee.zee5app.repo.UserRepoImpl;

public class UserServiceImpl implements UserService {

	private UserServiceImpl() {

    }

    private static UserService userService;

    public static UserService getInstance () {
        if(userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }


    private UserRepo repo = UserRepoImpl.getInstance();
	@Override
	public User insertuser(User user) throws UnableToGenerateIdException {
		// TODO Auto-generated method stub
		return repo.insertUser(user);
	}

	@Override
	public String updateUser(String userId, User user) throws InvalidIdException  {
		// TODO Auto-generated method stub
		return repo.updateUser(userId, user);
	}

	@Override
	public String deleteUser(String userId) throws NoDataFoundException {
		// TODO Auto-generated method stub
		return repo.deleteUserById(userId);
	}

	@Override
	public  Optional<List<User>> getAllUser() {
		// TODO Auto-generated method stub
		return repo.getAllUsers();
	}

	@Override
	public Optional<User> getUserByUserId(String UserId) {
		// TODO Auto-generated method stub
		return repo.getUserById(UserId);
	}

}
