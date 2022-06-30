package com.inkathon.OnlineBedAvailabilityTracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inkathon.OnlineBedAvailabilityTracker.dto.ResponseDto;
import com.inkathon.OnlineBedAvailabilityTracker.repositories.CityRepository;

@Service
public class CityServiceImpl implements CityService{
	
	@Autowired
	private CityRepository cityRepo;
	
	@Override
	public ResponseDto getCitydata() {
		ResponseDto dtoObj = new ResponseDto();
		try {
			dtoObj.setData(cityRepo.findAll());
			dtoObj.setStatus(true);
			dtoObj.setStatuscode(200);
			dtoObj.setMessage("success");

		} catch (Exception e) {
			dtoObj.setStatus(false);
			dtoObj.setStatuscode(500);
			dtoObj.setMessage(e.getMessage());

		}
		return dtoObj;
	}

}
