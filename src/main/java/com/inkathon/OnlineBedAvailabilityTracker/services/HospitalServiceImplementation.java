package com.inkathon.OnlineBedAvailabilityTracker.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.inkathon.OnlineBedAvailabilityTracker.dto.AddressDto;
import com.inkathon.OnlineBedAvailabilityTracker.dto.BedsDto;
import com.inkathon.OnlineBedAvailabilityTracker.dto.CityDto;
import com.inkathon.OnlineBedAvailabilityTracker.dto.HospitalDto;
import com.inkathon.OnlineBedAvailabilityTracker.dto.ResponseDto;
import com.inkathon.OnlineBedAvailabilityTracker.entities.Address;
import com.inkathon.OnlineBedAvailabilityTracker.entities.Beds;
import com.inkathon.OnlineBedAvailabilityTracker.entities.City;
import com.inkathon.OnlineBedAvailabilityTracker.entities.Hospital;
import com.inkathon.OnlineBedAvailabilityTracker.repositories.AddressRepository;
import com.inkathon.OnlineBedAvailabilityTracker.repositories.BedsRepository;
import com.inkathon.OnlineBedAvailabilityTracker.repositories.CityRepository;
import com.inkathon.OnlineBedAvailabilityTracker.repositories.HospitalRepository;

@Service
public class HospitalServiceImplementation implements HospitalService {

	@Autowired
	private HospitalRepository hospitalRepository;

	@Autowired
	private AddressRepository addressRepo;

	@Autowired
	private CityRepository cityRepo;

	@Autowired
	private BedsRepository bedsRepository;

	@Transactional
	@Override
	// after singup/login completes make a get request to (getuserInfo) and get
	// userid and pass it along with hospital dto
	public ResponseDto addNewHospital(HospitalDto hospitaldto, Long adminUserId) {
		
		ResponseDto dtoObj = new ResponseDto();
		try {
			Date timestamp = new Date();
			hospitaldto.setLastUpdated(timestamp);
			hospitaldto.setUserId(adminUserId);
			Hospital hospital = hospitalRepository.save(hospitalRepository.importdto(hospitaldto));
			long hospitalId = hospital.getHospitalId();

			// h - hid
			// a - hid
			// a.hid = h.hid
			//
			// Beds
			List<BedsDto> bedsDtoList = hospitaldto.getBedsListDto();

			for (int i = 0; i < bedsDtoList.size(); i++) {
				bedsDtoList.get(i).getBedId().setHospitalId(hospitalId);
			}

			for (BedsDto b : bedsDtoList) {
				// to update existing entry
				b.getBedId().setHospitalId(hospitalId);
				;
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

			// Address
			AddressDto addressdto = hospitaldto.getAddressDto();
			addressdto.sethospitalId(hospitalId);
			if (!cityRepo.existsById(addressdto.getCityId())) {
				throw new IllegalStateException("Invalid City Name!");
			}
			addressRepo.save(addressRepo.importdto(addressdto));
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

		

	@Transactional
	@Override
	public ResponseDto deleteHospital(long hospitalId) {
		
		ResponseDto dtoObj = new ResponseDto();
		try {
			boolean exists = hospitalRepository.existsById(hospitalId);
			if (!exists) {
				throw new IllegalStateException("Hospital with id " + hospitalId + "does not exists");
			}
			hospitalRepository.deleteById(hospitalId);
			addressRepo.deleteById(hospitalId);
			bedsRepository.deleteAllBedsByHospitalId(hospitalId);
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
		

	@Override
	public ResponseDto getHospital(int pageSize, int pageNumber) {
		ResponseDto dtoObj = new ResponseDto();
		Pageable page = PageRequest.of(pageNumber, pageSize);
		try {

			// dtoObj.setData(hospitalRepository.findAll(page)); [obj, obj]
			// obj =[1, name, city]
			List<Object[]> hospitalDataList = hospitalRepository.findAllHospitals(page);
			List<HospitalDto> hospitaldtolist = new ArrayList<HospitalDto>();
			if (!hospitalDataList.isEmpty()) {
				for (Object[] arr : hospitalDataList) {
					HospitalDto hospitaldtoObj = new HospitalDto();
					for (int i = 0; i < arr.length; i++) {
						hospitaldtoObj.setHospitalId((long) arr[0]);
						hospitaldtoObj.setHospitalName((String) arr[1]);
						hospitaldtoObj.setCategory((String) arr[2]);
						hospitaldtoObj.setLastUpdated((Date) (arr[3]));
						hospitaldtoObj.setOwnership((String) arr[4]);
						AddressDto adto = new AddressDto();
						hospitaldtoObj.setAddressDto(adto);
						CityDto cdto = new CityDto();
						hospitaldtoObj.getAddressDto().setCity(cdto);
						hospitaldtoObj.getAddressDto().getCity().setCityName((String) arr[5]);
						hospitaldtoObj.getAddressDto().getCity().setState((String) arr[6]);
					}
					hospitaldtolist.add(hospitaldtoObj);
				}
			}
			dtoObj.setData(hospitaldtolist);
			dtoObj.setStatus(true);
			dtoObj.setStatuscode(200);
			dtoObj.setMessage("success");

		} catch (Exception e) {
			dtoObj.setStatus(false);
			dtoObj.setStatuscode(500);
			dtoObj.setMessage(e.getLocalizedMessage());

		}
		return dtoObj;
	}

	@Override
	public ResponseDto listAllHospital(String keyword) {
		ResponseDto dtoObj = new ResponseDto();
		try {
			
			if (keyword != null) {
				List<Object[]> hospitalDataList = hospitalRepository.searchAll(keyword.toLowerCase());
				List<HospitalDto> hospitaldtolist = new ArrayList<HospitalDto>();
				if (!hospitalDataList.isEmpty()) {
					for (Object[] arr : hospitalDataList) {
						HospitalDto hospitaldtoObj = new HospitalDto();
						for (int i = 0; i < arr.length; i++) {
							hospitaldtoObj.setHospitalId((long) arr[0]);
							hospitaldtoObj.setHospitalName((String) arr[1]);
							hospitaldtoObj.setCategory((String) arr[2]);
							hospitaldtoObj.setLastUpdated((Date) (arr[3]));
							hospitaldtoObj.setOwnership((String) arr[4]);
							
							AddressDto adto = new AddressDto();
							hospitaldtoObj.setAddressDto(adto);
					
							CityDto cdto = new CityDto();
							hospitaldtoObj.getAddressDto().setCity(cdto);
							hospitaldtoObj.getAddressDto().getCity().setCityName((String) arr[5]);
							hospitaldtoObj.getAddressDto().getCity().setState((String) arr[6]);
							
						}
						hospitaldtolist.add(hospitaldtoObj);
					}
				}

				dtoObj.setData(hospitaldtolist);
				dtoObj.setStatus(true);
				dtoObj.setStatuscode(200);
				dtoObj.setMessage("success");

			}
		} catch (Exception e) {
			dtoObj.setStatus(false);
			dtoObj.setStatuscode(500);
			dtoObj.setMessage(e.getMessage());

		}
		return dtoObj;
	}

	@Override
	public ResponseDto getHospitaIDetails(long hospitalId) {
		ResponseDto dtoObj = new ResponseDto();
		try {
			Hospital hospital = hospitalRepository.findById(hospitalId)
					.orElseThrow(() -> new IllegalStateException("Hospital with Id " + hospitalId + "does not exists"));
			HospitalDto hospitaldto = hospitalRepository.exportdto(hospital);
			Address address = addressRepo.findById(hospital.getHospitalId())
					.orElseThrow(() -> new IllegalStateException());
			City city = cityRepo.findById(address.getCityId()).orElseThrow(() -> new IllegalStateException());

			List<Beds> bedsList = bedsRepository.findAllBedsByHospitalId(hospitalId);
			List<BedsDto> bedsDtoList = new ArrayList<>();
			for (Beds b : bedsList) {
				bedsDtoList.add(bedsRepository.exportdto(b));
			}
			hospitaldto.setBedsListDto(bedsDtoList);
			hospitaldto.setAddressDto(addressRepo.exportdto(address));
			hospitaldto.getAddressDto().setCity(cityRepo.exportdto(city));
			dtoObj.setData(hospitaldto);
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

	@Transactional
	@Override
	public ResponseDto updateHospital(HospitalDto hospitaldto) {
		
		ResponseDto dtoObj = new ResponseDto();
		try {
			Hospital hospital = hospitalRepository.findById(hospitaldto.getHospitalId()).orElseThrow(
					() -> new IllegalStateException("Hospital with Id " + hospitaldto.getHospitalId() + "does not exists"));

			if (hospitaldto.getHospitalName() != null && hospitaldto.getHospitalName().length() > 0
					&& !Objects.equals(hospital.getHospitalName(), hospitaldto.getHospitalName())) {
				hospital.setHospitalName(hospital.getHospitalName());
			}

			if (hospitaldto.getContactNumber() != 0
					&& !Objects.equals(hospital.getContactNumber(), hospitaldto.getContactNumber())) {
				hospital.setContactNumber(hospitaldto.getContactNumber());
			}

			if (hospitaldto.getEmail() != null && hospitaldto.getEmail().length() > 0
					&& !Objects.equals(hospital.getEmail(), hospitaldto.getEmail())) {
				hospital.setEmail(hospitaldto.getEmail());
			}

			if (hospitaldto.getOwnership() != null && hospitaldto.getOwnership().length() > 0
					&& !Objects.equals(hospital.getOwnership(), hospitaldto.getOwnership())) {
				hospital.setOwnership(hospitaldto.getOwnership());
			}

			if (hospitaldto.getCategory() != null && hospitaldto.getCategory().length() > 0
					&& !Objects.equals(hospital.getCategory(), hospitaldto.getCategory())) {
				hospital.setCategory(hospitaldto.getCategory());
			}

			

			AddressDto addressNew = hospitaldto.getAddressDto();
			Address address = addressRepo.findById(addressNew.gethospitalId()).orElseThrow(
					() -> new IllegalStateException("Hospital with Id " + addressNew.gethospitalId() + "does not exists"));
			if (addressNew.getAddressLine().length() > 0
					&& !Objects.equals(address.getAddressLine(), addressNew.getAddressLine())) {
				address.setAddressLine(addressNew.getAddressLine());
			}

			if (addressNew.getCityId() != 0 && !Objects.equals(address.getCityId(), addressNew.getCityId())) {
				address.setCityId(addressNew.getCityId());
			}
			if (!Objects.equals(address.getLatitude(), addressNew.getLatitude())) {
				address.setLatitude(addressNew.getLatitude());
			}
			if (!Objects.equals(address.getLongitude(), addressNew.getLongitude())) {
				address.setLongitude(addressNew.getLongitude());
			}
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

		

	@Override
	public ResponseDto getCategories() {
		ResponseDto dtoObj = new ResponseDto();
		try {
			dtoObj.setData(hospitalRepository.findAllCategories());
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

	@Override
	public ResponseDto getHospitalByAdmin(long adminId) {
		ResponseDto dtoObj = new ResponseDto();
		try {
			List<Hospital> list = hospitalRepository.findAllByUserId(adminId);
			List<HospitalDto> dtolist = new ArrayList<>();
			if (!list.isEmpty()) {
				for (Hospital h : list) {
					dtolist.add(hospitalRepository.exportdto(h));
				}
			}
			dtoObj.setData(dtolist);
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
