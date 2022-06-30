package com.inkathon.OnlineBedAvailabilityTracker.repositories;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inkathon.OnlineBedAvailabilityTracker.dto.BedsDto;
import com.inkathon.OnlineBedAvailabilityTracker.entities.BedId;
import com.inkathon.OnlineBedAvailabilityTracker.entities.Beds;

@Repository
public interface BedsRepository extends JpaRepository<Beds, BedId> {

	public default Beds importdto(BedsDto dto) {
		Beds beds = new Beds();
		if (dto != null) {
			BeanUtils.copyProperties(dto, beds);
		}
		return beds;
	}
	
	public default BedsDto exportdto(Beds beds) {
		BedsDto bedsdto = new BedsDto();
		if (beds != null) {
			BeanUtils.copyProperties(beds, bedsdto);
		}
		return bedsdto;
	}
	
	
	@Modifying 
    @Query(value = "DELETE FROM BEDS_TABLE WHERE hospital_id = :hospitalId",nativeQuery = true)
    int deleteAllBedsByHospitalId(@Param("hospitalId") long hospitalId);
	
	@Modifying 
    @Query(value = "SELECT * FROM BEDS_TABLE WHERE hospital_id = :hospitalId",nativeQuery = true)
    List<Beds> findAllBedsByHospitalId(@Param("hospitalId") long hospitalId);
	
	@Query("SELECT DISTINCT(LOWER(b.bedId.bedType)), SUM(b.totalBeds), SUM(b.occupiedBeds) from Beds b GROUP BY b.bedId.bedType ")
	List<Object[]> bedsDashboardData ();
}
