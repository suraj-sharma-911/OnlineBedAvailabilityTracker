package com.inkathon.OnlineBedAvailabilityTracker.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inkathon.OnlineBedAvailabilityTracker.dto.HospitalDto;
import com.inkathon.OnlineBedAvailabilityTracker.entities.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

	public default Hospital importdto(HospitalDto dto) {
		Hospital hospital = new Hospital();
		if (dto != null) {
			BeanUtils.copyProperties(dto, hospital);
		}
		return hospital;
	}

	public default HospitalDto exportdto(Hospital hospital) {
		HospitalDto dto = new HospitalDto();
		if (hospital != null) {
			BeanUtils.copyProperties(hospital, dto);
		}
		return dto;

	}

	Optional<Hospital> findHospitalByEmail(String email);
	
	public List<Hospital> findByUserId(long adminUserId);
	Optional<Hospital> deleteAllByUserId(long adminUserId);
	
	
	@Query("SELECT h.hospitalId, h.hospitalName, h.category, h.lastUpdated, h.ownership, "
			+ "c.cityName, c.state "
			+ "FROM Hospital h "
			+ "INNER JOIN Address a "
			+ "ON h.hospitalId = a.hospitalId "
			+ "INNER JOIN City c "
			+ "ON a.cityId = c.cityId")
	List<Object[]> findAllHospitals(Pageable page);
	
	@Query("SELECT DISTINCT(h.category) From Hospital h")
	List<String> findAllCategories();
	
	
	@Query("SELECT h.hospitalId, h.hospitalName, h.category, h.lastUpdated, h.ownership, "
			+ "c.cityName, c.state "
			+ "FROM Hospital h "
			+ "INNER JOIN Beds b "
			+ "ON h.hospitalId = b.bedId.hospitalId "
			+ "INNER JOIN Address a "
			+ "ON b.bedId.hospitalId = a.hospitalId "
			+ "INNER JOIN City c "
			+ "ON a.cityId = c.cityId "
			+ "WHERE LOWER(h.hospitalName) LIKE %?1% OR LOWER(h.ownership) LIKE %?1% OR LOWER(h.category) LIKE %?1% OR "
			+ "LOWER(c.cityName) LIKE %?1% OR LOWER(c.state) LIKE %?1% OR LOWER(b.bedId.bedType) LIKE %?1% "
			+ "GROUP BY h.hospitalId, h.hospitalName, h.category, h.lastUpdated, h.ownership, "
			+ "c.cityName, c.state") 
	List<Object[]> searchAll(@Param("keyword") String keyword);

	@Query
	List<Hospital> findAllByUserId(long adminId);

}
