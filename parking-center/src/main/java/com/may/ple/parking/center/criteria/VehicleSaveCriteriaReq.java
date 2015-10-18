package com.may.ple.parking.center.criteria;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class VehicleSaveCriteriaReq {
	private Integer licenseNo;
	private String deviceId;
	private String gateName;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
	
	public Integer getLicenseNo() {
		return licenseNo;
	}
	
	public void setLicenseNo(Integer licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getGateName() {
		return gateName;
	}

	public void setGateName(String gateName) {
		this.gateName = gateName;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

}
