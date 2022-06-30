package com.inkathon.OnlineBedAvailabilityTracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inkathon.OnlineBedAvailabilityTracker.dto.BedsDto;
import com.inkathon.OnlineBedAvailabilityTracker.dto.ResponseDto;
import com.inkathon.OnlineBedAvailabilityTracker.entities.BedId;
import com.inkathon.OnlineBedAvailabilityTracker.services.BedsService;

@RestController
@RequestMapping(path="/admin") //change this if any error occurs due to path
public class BedsController {
	@Autowired
	private BedsService bedsService;
	
	
	@PostMapping
	public ResponseDto addUpdateBeds(@RequestBody List<BedsDto> bedsdtolist) {
		return bedsService.addNewBedUpdate(bedsdtolist);
	}
	
	@DeleteMapping
	public ResponseDto deleteBed(@RequestBody BedId bedId) {
		return bedsService.deleteOneBedType(bedId);
	}

}
