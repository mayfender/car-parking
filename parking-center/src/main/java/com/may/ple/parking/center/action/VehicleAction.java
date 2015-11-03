package com.may.ple.parking.center.action;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.may.ple.parking.center.criteria.VehicleCheckOutCriteriaReq;
import com.may.ple.parking.center.criteria.VehicleCheckOutCriteriaResp;
import com.may.ple.parking.center.criteria.VehicleSaveCriteriaReq;
import com.may.ple.parking.center.criteria.VehicleSaveCriteriaResp;
import com.may.ple.parking.center.criteria.VehicleSearchCriteriaReq;
import com.may.ple.parking.center.criteria.VehicleSearchCriteriaResp;
import com.may.ple.parking.center.entity.VehicleParking;
import com.may.ple.parking.center.exception.CustomerException;
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
	@Path("/searchVehicleParking")
	@Produces(MediaType.APPLICATION_JSON)
	public VehicleSearchCriteriaResp searchVehicleParking(VehicleSearchCriteriaReq req) {
		VehicleSearchCriteriaResp resp;
		LOG.debug("Start");
		
		try {
			LOG.debug(req);
			
			resp = service.searchVehicleParking(req);
			
			LOG.debug(resp);
		} catch (Exception e) {
			resp = new VehicleSearchCriteriaResp();
			resp.setStatusCode(1000);
			LOG.error(e.toString());
		}
		
		LOG.debug("End");
		return resp;
	}
	
	@POST
	@Path("/checkOutVehicle")
	@Produces(MediaType.APPLICATION_JSON)
	public VehicleCheckOutCriteriaResp checkOutVehicle(VehicleCheckOutCriteriaReq req) {
		VehicleCheckOutCriteriaResp resp = new VehicleCheckOutCriteriaResp();
		LOG.debug("Start");
		
		try {
			LOG.debug(req);
			
			VehicleParking vehicleParking = service.checkOutVehicle(req);
			resp.setVehicleParking(vehicleParking);
			
			LOG.debug(resp);
		} catch (CustomerException e) {
			resp.setStatusCode(e.errCode);			
			LOG.error(e.toString());
		} catch (Exception e) {
			resp.setStatusCode(1000);
			LOG.error(e.toString());
		}
		
		LOG.debug("End");
		return resp;
	}
	
	@POST
	@Path("/saveVehicleParking")
	@Produces(MediaType.APPLICATION_JSON)
	public VehicleSaveCriteriaResp saveVehicleParking(VehicleSaveCriteriaReq req) {
		VehicleSaveCriteriaResp resp = new VehicleSaveCriteriaResp();
		LOG.debug("Start");
		
		try {
			LOG.debug(req);
			
			service.saveVehicleParking(req);
			
			LOG.debug(resp);
		} catch (Exception e) {
			resp.setStatusCode(1000);
			LOG.error(e.toString());
		}
		
		LOG.debug("End");
		return resp;
	}
	
}
