package com.inkathon.OnlineBedAvailabilityTracker.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inkathon.OnlineBedAvailabilityTracker.dto.AddressDto;
import com.inkathon.OnlineBedAvailabilityTracker.dto.HospitalDto;
import com.inkathon.OnlineBedAvailabilityTracker.dto.ResponseDto;
import com.inkathon.OnlineBedAvailabilityTracker.dto.UserDto;
import com.inkathon.OnlineBedAvailabilityTracker.entities.Address;
import com.inkathon.OnlineBedAvailabilityTracker.entities.Hospital;
import com.inkathon.OnlineBedAvailabilityTracker.entities.User;
import com.inkathon.OnlineBedAvailabilityTracker.repositories.AddressRepository;
import com.inkathon.OnlineBedAvailabilityTracker.repositories.BedsRepository;
import com.inkathon.OnlineBedAvailabilityTracker.repositories.HospitalRepository;
import com.inkathon.OnlineBedAvailabilityTracker.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HospitalRepository hospitalRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private BedsRepository bedsRepository;
	
	@Override
	public ResponseDto getUserInfo(String username, String password) throws NoSuchAlgorithmException{
		ResponseDto dto = new ResponseDto();
			try {
				User user = userRepository.findByUsername(username);
				
				String existingPasswordHash = user.getPassword();
			    
			    MessageDigest md = MessageDigest.getInstance("MD5");
			    md.update(password.getBytes());
			    byte[] digest = md.digest();
			    String CurrentPasswordHash = DatatypeConverter
			      .printHexBinary(digest).toUpperCase();
			        
//			    assertThat(existingPasswordHash.equals(CurrentPasswordHash)).isTrue();
				if(!existingPasswordHash.equals(CurrentPasswordHash)){
						throw new IllegalStateException("Wrong Password!");
				}
				
				if(user.isAdmin()){
					UserDto userdto = userRepository.exportdto(user);
					List<Hospital> hospital = hospitalRepository.findByUserId(user.getUserId());
					List<HospitalDto> hospitaldto=new ArrayList<HospitalDto>();
					if(!hospital.isEmpty()){
						for(Hospital h: hospital){
							HospitalDto dtoObj =hospitalRepository.exportdto(h);
							Address address = addressRepository.findById(dtoObj.getHospitalId()).orElseThrow(() -> new IllegalStateException());
							AddressDto addressdto = addressRepository.exportdto(address);
							dtoObj.setAddressDto(addressdto);
							hospitaldto.add(dtoObj);
						}
					}
					
					
					List<Object> data = new ArrayList<>(); 
					data.add(userdto);
					data.add(hospitaldto);
					dto.setData(data);
					dto.setStatus(true);
					dto.setStatuscode(200);
					dto.setMessage("success");
				}else{
					UserDto userdto = userRepository.exportdto(user);
					dto.setData(userdto);
					dto.setStatus(true);
					dto.setStatuscode(200);
					dto.setMessage("success");
				}
				
			} catch (Exception e) {
				dto.setStatus(false);
				dto.setStatuscode(500);
				dto.setMessage(e.getMessage());
			}
			
			return dto;
		}


	@Override
	public ResponseDto addNewUser(UserDto userdto) {
		
		ResponseDto dtoObj = new ResponseDto();
		try {
			if(userdto != null){
				if(userdto.getUsername()!= null){
					if(!userRepository.existsByUsername(userdto.getUsername())){
//						--------------> MD5 HASH generation <------------------
						MessageDigest md = MessageDigest.getInstance("MD5");
						md.update(userdto.getPassword().getBytes());
						byte[] digest = md.digest();
//						myHash -> hashcode
						String myHash = DatatypeConverter 
							      .printHexBinary(digest).toUpperCase();
						
						userdto.setPassword(myHash);
//						System.out.println(myHash);
					userRepository.save(userRepository.importdto(userdto));
					dtoObj.setData(null);
					dtoObj.setStatus(true);
					dtoObj.setStatuscode(200);
					dtoObj.setMessage("success");
					}else{
						throw new IllegalStateException("Username already exists!");
					}
				}else{
					throw new IllegalStateException("Username cannot be empty");
				}
			}

		} catch (Exception e) {
			dtoObj.setStatus(false);
			dtoObj.setStatuscode(500);
			dtoObj.setMessage(e.getMessage());

		}
		return dtoObj;
	}

	@Transactional
	@Override
	public ResponseDto deleteUser(Long userId) {
		
		ResponseDto dtoObj = new ResponseDto();
		try {
			boolean exists = userRepository.existsById(userId);
			if (!exists) {
				throw new IllegalStateException("User with Id " + userId + " does not exists");
			} else if (userRepository.getById(userId).isAdmin()) {
				
				List<Hospital> hospitalList = hospitalRepository.findByUserId(userId);
				hospitalRepository.deleteAllByUserId(userId);
				if(!hospitalList.isEmpty()){
					for(Hospital h: hospitalList){
						bedsRepository.deleteAllBedsByHospitalId(h.getHospitalId());
						addressRepository.deleteById(h.getHospitalId());
					}
				}
				userRepository.deleteById(userId);
			} else {
				userRepository.deleteById(userId);
				// if booking implemented delete booking table entries for userid
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
		

	@Transactional
	@Override
	public ResponseDto updateUser(UserDto userdto) {
		User user = userRepository.findById(userdto.getUserId())
				.orElseThrow(() -> new IllegalStateException("Invalid user Id"));
		ResponseDto dtoObj = new ResponseDto();
		try {
			if (userdto.getUsername() != null && userdto.getUsername().length() > 0
					&& !Objects.equals(user.getUsername(), userdto.getUsername())) {
				user.setUsername(userdto.getUsername());
			}

			if (userdto.getPassword() != null && userdto.getPassword().length() > 0) {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(userdto.getPassword().getBytes());
				byte[] digest = md.digest();
//				myHash -> hashcode
				String myHash = DatatypeConverter 
					      .printHexBinary(digest).toUpperCase();
				
				userdto.setPassword(myHash);
				user.setPassword(userdto.getPassword());
			}

			if (userdto.getEmail() != null && userdto.getEmail().length() > 0
					&& !Objects.equals(user.getEmail(), userdto.getEmail())) {
				user.setEmail(userdto.getEmail());
			}

			if (!Objects.equals(user.isAdmin(), userdto.isAdmin())) {
				user.setAdmin(userdto.isAdmin());
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

}
