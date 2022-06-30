package com.inkathon.OnlineBedAvailabilityTracker.services;

import java.util.List;

import com.inkathon.OnlineBedAvailabilityTracker.dto.BedsDto;
import com.inkathon.OnlineBedAvailabilityTracker.dto.ResponseDto;
import com.inkathon.OnlineBedAvailabilityTracker.entities.BedId;

public interface BedsService {

	ResponseDto deleteOneBedType(BedId bedId);

	ResponseDto addNewBedUpdate(List<BedsDto> bedsdto);
}
