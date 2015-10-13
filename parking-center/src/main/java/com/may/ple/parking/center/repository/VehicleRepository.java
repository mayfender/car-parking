package com.may.ple.parking.center.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.may.ple.parking.center.criteria.VehicleCriteriaReq;
import com.may.ple.parking.center.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	
	List<Vehicle> findVehicle(VehicleCriteriaReq req);
	
}
