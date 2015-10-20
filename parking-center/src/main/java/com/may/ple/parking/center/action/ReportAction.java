package com.may.ple.parking.center.action;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.may.ple.parking.center.criteria.ReportCriteriaResp;
import com.may.ple.parking.center.service.ReportService;

@Component
@Path("report")
public class ReportAction {
	private static final Logger LOG = Logger.getLogger(ReportAction.class.getName());
	private ReportService reportService;
	
	@Autowired
	public ReportAction(ReportService reportService) {
		this.reportService = reportService;
	}
	
	@GET
	@Path("/loadReport")
	@Secured("ROLE_ADMIN")
	public ReportCriteriaResp loadReport() {
		ReportCriteriaResp resp = new ReportCriteriaResp();
		LOG.debug("Start");
		
		try {
			
			
			LOG.debug(resp);
		} catch (Exception e) {
			resp.setStatusCode(1000);
			LOG.error(e.toString());
		}
		
		LOG.debug("End");
		return resp;
	}

}
