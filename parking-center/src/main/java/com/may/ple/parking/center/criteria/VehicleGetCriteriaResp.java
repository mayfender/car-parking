package com.may.ple.parking.center.criteria;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class VehicleGetCriteriaResp extends CommonCriteriaResp {
	public String id;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
	
}
