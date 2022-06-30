package com.inkathon.OnlineBedAvailabilityTracker.services;

import com.inkathon.OnlineBedAvailabilityTracker.dto.HospitalDto;
import com.inkathon.OnlineBedAvailabilityTracker.dto.ResponseDto;

public interface HospitalService {
	ResponseDto addNewHospital(HospitalDto hospital, Long adminUserId);

	ResponseDto deleteHospital(long hospitalId);

	ResponseDto getHospital(int pageSize, int pageNumber);
	
	ResponseDto listAllHospital(String keyword);

	ResponseDto getHospitaIDetails(long id);

	ResponseDto updateHospital(HospitalDto hospitaldto);

	ResponseDto getCategories();

	ResponseDto getHospitalByAdmin(long adminId);
}
