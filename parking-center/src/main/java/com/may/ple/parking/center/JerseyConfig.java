package com.may.ple.parking.center;

import javax.ws.rs.ApplicationPath;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.may.ple.parking.center.action.ReportAction;
import com.may.ple.parking.center.action.SettingAction;
import com.may.ple.parking.center.action.UserAction;
import com.may.ple.parking.center.action.VehicleAction;

@Component
@ApplicationPath(value="/restAct")
public class JerseyConfig extends ResourceConfig {
	private static final Logger LOG = Logger.getLogger(JerseyConfig.class.getName());
	
	public JerseyConfig() {
		LOG.debug(":----------: Register Rest Service :----------:");
		register(UserAction.class).
		register(VehicleAction.class).
		register(SettingAction.class).
		register(ReportAction.class);
	}

}
