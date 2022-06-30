package com.inkathon.OnlineBedAvailabilityTracker.services;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inkathon.OnlineBedAvailabilityTracker.dto.BedsDto;
import com.inkathon.OnlineBedAvailabilityTracker.dto.ResponseDto;
import com.inkathon.OnlineBedAvailabilityTracker.entities.BedId;
import com.inkathon.OnlineBedAvailabilityTracker.entities.Beds;
import com.inkathon.OnlineBedAvailabilityTracker.entities.Hospital;
import com.inkathon.OnlineBedAvailabilityTracker.repositories.BedsRepository;
import com.inkathon.OnlineBedAvailabilityTracker.repositories.HospitalRepository;

@Service
public class BedsServiceImplementation implements BedsService {

	@Autowired
	private BedsRepository bedsRepository;

	@Autowired
	private HospitalRepository hospitalRepository;

	
	@Transactional
	@Override
	public ResponseDto addNewBedUpdate(List<BedsDto> bedsdto) {
		
		ResponseDto dtoObj = new ResponseDto();
		try {
			for (BedsDto b : bedsdto) {
				b.getBedId().setBedType(b.getBedId().getBedType().toUpperCase());
				// to update existing entry
				if (bedsRepository.existsById(b.getBedId())) {
					Beds beds = bedsRepository.findById(b.getBedId()).orElseThrow(
							() -> new IllegalStateException("Bed entry with Id " + b.getBedId() + "does not exists"));

					if (!Objects.equals(beds.getOccupiedBeds(), b.getOccupiedBeds())) {
						beds.setOccupiedBeds(b.getOccupiedBeds());
					}

					if (!Objects.equals(beds.getTotalBeds(), b.getTotalBeds())) {
						beds.setTotalBeds(b.getTotalBeds());
					}
					if (!Objects.equals(beds.getBudget(), b.getBudget())) {
						beds.setBudget(b.getBudget());
					}
					continue;
				} else {
					// to save new entry
					bedsRepository.save(bedsRepository.importdto(b));
				}
			}
			// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date timestamp = new Date();
			Hospital hospital = hospitalRepository.findById(bedsdto.get(0).getBedId().getHospitalId())
					.orElseThrow(() -> new IllegalStateException("Hospital with Id does not exists"));
			hospital.setLastUpdated(timestamp);
			dtoObj.setData(null);
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


	public ResponseDto deleteOneBedType(BedId bedId) {
		ResponseDto dtoObj = new ResponseDto();
		try {
			bedsRepository.deleteById(bedId);
			dtoObj.setData(null);
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
		
	// @Transactional
	// @Override
	// public void updateBed(BedsDto bedDto){
	// Beds beds=bedsRepository.findById(bedDto.get)
	// .orElseThrow(()-> new IllegalStateException(
	// "Bed with Id "+hospitalId+"does not exists"));
	//
	// if(hospitalId != 0 &&
	// !Objects.equals(beds.getHospitalId(), hospitalId)) {
	// beds.setHospitalId(hospitalId);
	// }
	//
	//
	// if(occupiedBeds != 0 &&
	// !Objects.equals(beds.getOccupiedBeds(), occupiedBeds)) {
	// beds.setOccupiedBeds(occupiedBeds);
	// }
	//
	// if(bedType != null &&
	// bedType.length() > 0 &&
	// !Objects.equals(beds.getBedType(), bedType)) {
	// beds.setBedType(bedType);
	// }
	//
	// /*if(bedId != 0 &&
	// !Objects.equals(beds.getBedId(), bedId)) {
	// beds.setBedId(bedId);
	// }*/
	//
	// if(totalBeds != 0 &&
	// !Objects.equals(beds.getTotalBeds(), totalBeds)) {
	// beds.setTotalBeds(totalBeds);
	// }
	// }

	// @Override
	// public ResponseDto findBed() {
	// ResponseDto dtoObj = new ResponseDto();
	// try {
	// dtoObj.setData(bedsRepository.findAll());
	// dtoObj.setStatus(true);
	// dtoObj.setStatuscode(500);
	// dtoObj.setMessage("Success");
	//
	// } catch (Exception e) {
	// dtoObj.setStatus(false);
	// dtoObj.setStatuscode(200);
	// dtoObj.setMessage(e.getMessage());
	// }
	// return dtoObj;
	// }

}
