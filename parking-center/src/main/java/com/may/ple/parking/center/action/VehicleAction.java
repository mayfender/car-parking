package com.may.ple.parking.center.action;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.may.ple.parking.center.criteria.VehicleCriteriaReq;
import com.may.ple.parking.center.criteria.VehicleCriteriaResp;
import com.may.ple.parking.center.custom.PropertiesCustom;
import com.may.ple.parking.center.service.VehicleService;

@Component
@Path("vehicle")
public class VehicleAction {
	private static final Logger LOG = Logger.getLogger(VehicleAction.class.getName());
	private VehicleService service;
	private PropertiesCustom dbProp;
	
	@Autowired
	public VehicleAction(VehicleService service, PropertiesCustom dbProp) {
		this.service = service;
		this.dbProp = dbProp;
	}
	
	@POST
	@Path("/findVehicleParking")
	@Produces(MediaType.APPLICATION_JSON)
	@Secured("ROLE_ADMIN")
	public VehicleCriteriaResp findVehicleParking(VehicleCriteriaReq req) {
		VehicleCriteriaResp resp;
		LOG.debug("Start");
		
		try {
			LOG.debug(req);
			
			resp = service.findVehicleParking(req);
			
			LOG.debug(dbProp.getDouble("price.hour"));
			
			LOG.debug(resp);
		} catch (Exception e) {
			resp = new VehicleCriteriaResp();
			resp.setStatusCode(1000);
			LOG.error(e.toString());
		}
		
		LOG.debug("End");
		return resp;
	}
	
}
