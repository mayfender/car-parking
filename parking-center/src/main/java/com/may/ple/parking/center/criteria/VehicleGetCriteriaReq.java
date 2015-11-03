package com.may.ple.parking.center.criteria;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class VehicleGetCriteriaReq {
	public String id;
	public String licenseNo;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
	
}
