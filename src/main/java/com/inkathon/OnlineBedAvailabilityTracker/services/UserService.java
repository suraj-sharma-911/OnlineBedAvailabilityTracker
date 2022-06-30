package com.inkathon.OnlineBedAvailabilityTracker.services;

import java.security.NoSuchAlgorithmException;

import com.inkathon.OnlineBedAvailabilityTracker.dto.ResponseDto;
import com.inkathon.OnlineBedAvailabilityTracker.dto.UserDto;

public interface UserService {
	
	ResponseDto getUserInfo(String username, String password)  throws NoSuchAlgorithmException  ;
	
	ResponseDto addNewUser(UserDto userdto);

	ResponseDto deleteUser(Long userId);

	ResponseDto updateUser(UserDto userdto);
	

}
