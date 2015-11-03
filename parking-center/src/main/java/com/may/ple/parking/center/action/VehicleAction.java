package com.may.ple.parking.center.action;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.may.ple.parking.center.criteria.VehicleGetCriteriaReq;
import com.may.ple.parking.center.criteria.VehicleGetCriteriaResp;
import com.may.ple.parking.center.criteria.VehicleSaveCriteriaReq;
import com.may.ple.parking.center.criteria.VehicleSaveCriteriaResp;
import com.may.ple.parking.center.criteria.VehicleSearchCriteriaReq;
import com.may.ple.parking.center.criteria.VehicleSearchCriteriaResp;
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
	@Path("/getVehicleParking")
	@Produces(MediaType.APPLICATION_JSON)
	public VehicleGetCriteriaResp getVehicleParking(VehicleGetCriteriaReq req) {
		VehicleGetCriteriaResp resp = new VehicleGetCriteriaResp();
		LOG.debug("Start");
		
		try {
			LOG.debug(req);
			
			service.findVehicleParking(req);
			resp.id = "010000000000";
			
			LOG.debug(resp);
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
