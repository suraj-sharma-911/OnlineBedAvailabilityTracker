package com.inkathon.OnlineBedAvailabilityTracker.repositories;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inkathon.OnlineBedAvailabilityTracker.dto.CityDto;
import com.inkathon.OnlineBedAvailabilityTracker.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
	
	public default CityDto exportdto(City city) {
		CityDto dto = new CityDto();
		if (city != null) {
			BeanUtils.copyProperties(city, dto);
		}
		return dto;

	}
	
	@Query("Select c.cityName from City c where c.cityId = :cityId")
	String getCityName(@Param("cityId") long cityId);
	
	@Query("Select c.state from City c where c.cityId = :cityId")
	String getStateName(@Param("cityId") long cityId);
}
