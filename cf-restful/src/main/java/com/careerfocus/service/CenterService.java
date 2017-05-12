package com.careerfocus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careerfocus.constants.ErrorCodes;
import com.careerfocus.entity.Center;
import com.careerfocus.entity.Student;
import com.careerfocus.repository.AddressRepository;
import com.careerfocus.repository.CenterRepository;
import com.careerfocus.util.response.Error;
import com.careerfocus.util.response.Response;

@Service
public class CenterService {

	@Autowired
	CenterRepository cRepository;
	
	@Autowired
	AddressRepository addressRepository;

	public Response saveCenter(Center center) {
		if (cRepository.findByCenterCode(center.getCenterCode()) != null) {
			return centerAlreadyExistsResponse();
		}
		if (center.getAddress() != null)
			center.setAddress(addressRepository.save(center.getAddress()));
		return Response.ok(cRepository.save(center)).build();
	}

	public Center getCenter(int centerId) {
		return cRepository.findOne(centerId);
	}
	
	public List<Center> getAllCenters() {
		return cRepository.findAll();
	}

	public void deleteCenter(int centerId) {
		cRepository.delete(centerId);
	}
	
	public Set<Student> getAllStudentsInCenter(int centerId) {
		return cRepository.findOne(centerId).getStudents();
	}

	private Response centerAlreadyExistsResponse() {
		List<Error> subErrors = new ArrayList<Error>();
		subErrors.add(new Error(ErrorCodes.CENTER_ALREADY_EXISTS, ErrorCodes.CENTER_ALREADY_EXISTS_MSG));
		return Response.status(ErrorCodes.VALIDATION_FAILED)
				.error(new Error(ErrorCodes.VALIDATION_FAILED, ErrorCodes.VALIDATION_FAILED_MSG), subErrors).build();
	}

}
