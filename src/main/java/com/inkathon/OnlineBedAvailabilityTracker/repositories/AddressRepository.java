package com.inkathon.OnlineBedAvailabilityTracker.repositories;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inkathon.OnlineBedAvailabilityTracker.dto.AddressDto;
import com.inkathon.OnlineBedAvailabilityTracker.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	public default Address importdto(AddressDto dto) {
		Address address = new Address();
		if (dto != null) {
			BeanUtils.copyProperties(dto, address);
		}
		return address;
	}

	public default AddressDto exportdto(Address address) {
		AddressDto dto = new AddressDto();
		if (address != null) {
			BeanUtils.copyProperties(address, dto);
		}
		return dto;
	}
	

}
