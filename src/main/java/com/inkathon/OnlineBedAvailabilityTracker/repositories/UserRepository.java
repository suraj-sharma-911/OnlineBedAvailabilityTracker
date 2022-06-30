package com.inkathon.OnlineBedAvailabilityTracker.repositories;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inkathon.OnlineBedAvailabilityTracker.dto.UserDto;
import com.inkathon.OnlineBedAvailabilityTracker.entities.User;

@Repository //responsible for data access
public interface UserRepository extends JpaRepository<User, Long>{

	public default User importdto(UserDto dto){
		User user=new User();
		if (dto!=null ){
			BeanUtils.copyProperties(dto, user);
		}
		return user;
	}
	
	public default UserDto exportdto(User user){
		
		UserDto userdto =new UserDto();
		if (user!=null ){
			BeanUtils.copyProperties(user, userdto);
		}
		return userdto;
	}
	
	boolean existsByUsername(String username);
	
	User findByUsername(String username);
	 
}
