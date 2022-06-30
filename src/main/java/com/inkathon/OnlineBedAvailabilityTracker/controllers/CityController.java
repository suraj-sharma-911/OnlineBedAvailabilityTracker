package com.inkathon.OnlineBedAvailabilityTracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inkathon.OnlineBedAvailabilityTracker.dto.ResponseDto;
import com.inkathon.OnlineBedAvailabilityTracker.services.CityService;

@RestController
@RequestMapping(path = "api/hospital/city")
public class CityController {
	@Autowired
	CityService cityservice;
	
	@GetMapping
	ResponseDto getCities(){
		return cityservice.getCitydata();
	}
}
