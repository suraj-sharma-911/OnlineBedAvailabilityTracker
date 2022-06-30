package com.inkathon.OnlineBedAvailabilityTracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inkathon.OnlineBedAvailabilityTracker.dto.HospitalDto;
import com.inkathon.OnlineBedAvailabilityTracker.dto.ResponseDto;
import com.inkathon.OnlineBedAvailabilityTracker.services.DashboardService;
import com.inkathon.OnlineBedAvailabilityTracker.services.HospitalService;

@RestController
@RequestMapping(path = { "/api/hospital" })
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;
	
	@Autowired
	private DashboardService dashboardService;

	@GetMapping(path="/dashboard")
	public ResponseDto getDashboardData() {
		return dashboardService.getDashboardData();
	}
	
	@GetMapping
	public ResponseDto getHospital(@RequestParam(required = false, defaultValue = "5") int pageSize,
			@RequestParam(required = false, defaultValue = "0") int pageNumber) {
		return hospitalService.getHospital(pageSize, pageNumber);
	}
	
	@GetMapping(path = "/categories")
	public ResponseDto getCategories() {
		return hospitalService.getCategories();
	}
	
	@GetMapping(path = "/admin/{adminId}")
	public ResponseDto getHospitalByAdmin(@PathVariable("adminId") long adminId) {
		return hospitalService.getHospitalByAdmin(adminId);
	}

	@GetMapping(value = { "/search/{keyword}" })
	public ResponseDto listAllHospital(@PathVariable(value = "keyword", required = true) String keyword) {
		return hospitalService.listAllHospital(keyword);
	}

	@GetMapping(path = "/{hospitalId}")
	public ResponseDto gethospitalDetails(@PathVariable("hospitalId") long hospitalId) {
		return hospitalService.getHospitaIDetails(hospitalId);
	}

	@PostMapping
	public ResponseDto registerNewHospital(@RequestParam(required = true) long adminUserId,
			@RequestBody HospitalDto hospitaldto) {

		return hospitalService.addNewHospital(hospitaldto, adminUserId);
	}

	@DeleteMapping(path = "/{hospitalId}")
	public ResponseDto deleteHospital(@PathVariable("hospitalId") long hospitalId) {
		return hospitalService.deleteHospital(hospitalId);
	}

	@PutMapping()
	public ResponseDto updateHospital(@RequestBody HospitalDto hospitaldto) {
		return hospitalService.updateHospital(hospitaldto);
	}
}
