package com.may.ple.parking.center.action;

import java.util.List;

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
import com.may.ple.parking.center.entity.Vehicle;
import com.may.ple.parking.center.service.VehicleService;

@Component
@Path("vehicle")
public class VehicleAction {
	private static final Logger LOG = Logger.getLogger(VehicleAction.class.getName());
	private VehicleService service;
	
	@Autowired
	public VehicleAction(VehicleService service) {
		this.service = service;
	}
	
	@POST
	@Path("/findVehicle")
	@Produces(MediaType.APPLICATION_JSON)
	@Secured("ROLE_ADMIN")
	public VehicleCriteriaResp findVehicle(VehicleCriteriaReq req) {
		VehicleCriteriaResp resp = new VehicleCriteriaResp();
		LOG.debug("Start");
		
		try {
			List<Vehicle> vehicles = service.findVehicle(req);
			resp.setVehicles(vehicles);
			
			LOG.debug(resp);
		} catch (Exception e) {
			resp.setStatusCode(1000);
			LOG.error(e.toString());
		}
		
		LOG.debug("End");
		return resp;
	}
	
}
