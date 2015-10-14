package com.may.ple.parking.center.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.may.ple.parking.center.entity.VehicleParking;

public interface VehicleParkingRepository extends JpaRepository<VehicleParking, Long> {
	
	List<VehicleParking> findVehicleParking(Integer status);
	
}
