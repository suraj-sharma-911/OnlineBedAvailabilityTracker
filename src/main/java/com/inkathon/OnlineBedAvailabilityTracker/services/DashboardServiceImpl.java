package com.inkathon.OnlineBedAvailabilityTracker.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inkathon.OnlineBedAvailabilityTracker.dto.DashboardDto;
import com.inkathon.OnlineBedAvailabilityTracker.dto.ResponseDto;
import com.inkathon.OnlineBedAvailabilityTracker.repositories.BedsRepository;
import com.inkathon.OnlineBedAvailabilityTracker.repositories.HospitalRepository;

@Service
public class DashboardServiceImpl implements DashboardService{
	
	@Autowired
	HospitalRepository hospitalRepo;
	
	@Autowired
	BedsRepository bedsRepo;

	@Override
	public ResponseDto getDashboardData() {
		ResponseDto dtoObj = new ResponseDto();
		
		try { 
			List<DashboardDto> dto = new ArrayList <DashboardDto>();
			long count = hospitalRepo.count();

			List<Object[]> bedsData = bedsRepo.bedsDashboardData();
			if(!bedsData.isEmpty()){
				for(Object[] arr: bedsData){
					DashboardDto temp = new DashboardDto();
					temp.setBedType(arr[0].toString());
					temp.setOccupiedBeds((long)arr[2]);
					temp.setTotalBeds((long)arr[1]);
					temp.setTotalHospitals(count);
					dto.add(temp);
				}
				
			}
			dtoObj.setData(dto);
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
