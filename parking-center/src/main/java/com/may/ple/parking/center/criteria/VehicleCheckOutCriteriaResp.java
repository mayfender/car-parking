package com.may.ple.parking.center.criteria;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.may.ple.parking.center.entity.VehicleParking;

public class VehicleCheckOutCriteriaResp extends CommonCriteriaResp {
	private VehicleParking vehicleParking;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}

	public VehicleParking getVehicleParking() {
		return vehicleParking;
	}

	public void setVehicleParking(VehicleParking vehicleParking) {
		this.vehicleParking = vehicleParking;
	}

}
