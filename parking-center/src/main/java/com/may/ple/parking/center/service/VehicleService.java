package com.may.ple.parking.center.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.may.ple.parking.center.criteria.VehicleCriteriaReq;
import com.may.ple.parking.center.entity.Vehicle;
import com.may.ple.parking.center.repository.VehicleRepository;

@Service
public class VehicleService {
	private static final Logger LOG = Logger.getLogger(VehicleService.class.getName());
	private VehicleRepository vehicleRepository;
	
	@Autowired
	public VehicleService(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}
	
	public List<Vehicle> findVehicle(VehicleCriteriaReq req) {
		try {
			return vehicleRepository.findAll();
//			return vehicleRepository.findVehicle(req);
		} catch (Exception e) {
			LOG.error(e.toString());
			throw e;
		}
	}
	
}
