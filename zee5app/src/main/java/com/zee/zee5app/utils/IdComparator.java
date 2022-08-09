package com.zee.zee5app.utils;

import java.util.Comparator;

import com.zee.zee5app.dto.User;

public class IdComparator implements Comparator<User> {

	@Override
	public int compare(User user, User user2) {
		// TODO Auto-generated method stub
		return user.getUserId().compareTo(user2.getUserId());
	}

}
