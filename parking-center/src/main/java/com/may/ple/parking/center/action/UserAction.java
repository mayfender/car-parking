package com.may.ple.parking.center.action;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.may.ple.parking.center.criteria.CommonCriteriaResp;
import com.may.ple.parking.center.criteria.PersistUserCriteriaReq;
import com.may.ple.parking.center.criteria.ProfileUpdateCriteriaReq;
import com.may.ple.parking.center.criteria.UserSearchCriteriaResp;
import com.may.ple.parking.center.entity.Users;
import com.may.ple.parking.center.exception.CustomerException;
import com.may.ple.parking.center.service.UserService;

@Component
@Path("user")
public class UserAction {
	private static final Logger LOG = Logger.getLogger(UserAction.class.getName());
	private UserService service;
	
	@Autowired
	public UserAction(UserService service) {
		this.service = service;
	}
	
	@GET
	@Path("/findUserAll")
	@Produces(MediaType.APPLICATION_JSON)
	@Secured("ROLE_ADMIN")
	public UserSearchCriteriaResp findUserAll() {
		LOG.debug("Start");
		UserSearchCriteriaResp resp = new UserSearchCriteriaResp();
		
		try {
			List<Users> users = service.findAllUser();
			resp.setUsers(users);
		} catch (Exception e) {
			resp.setStatusCode(1000);
			LOG.error(e.toString());
		}
		
		LOG.debug("End");
		return resp;
	}
	
	@GET
	@Path("/deleteUser")
	@Secured("ROLE_ADMIN")
	public UserSearchCriteriaResp deleteUser(@QueryParam("userId")Long userId) {
		LOG.debug("Start");
		UserSearchCriteriaResp resp;
		
		try {
			LOG.debug("userId : " + userId);
			service.deleteUser(userId);
			resp = findUserAll();
		} catch (Exception e) {
			resp = new UserSearchCriteriaResp(1000);
			LOG.error(e.toString());
		}
		
		LOG.debug("End");
		return resp;
	}
	
	@POST
	@Path("/saveUser")
	@Secured("ROLE_ADMIN")
	public CommonCriteriaResp saveUser(PersistUserCriteriaReq req) {
		LOG.debug("Start");
		CommonCriteriaResp resp = new CommonCriteriaResp() {};
		
		try {
			LOG.debug(req);
			service.saveUser(req);
		} catch (CustomerException cx) {
			resp.setStatusCode(cx.errCode);
			LOG.error(cx.toString());
		} catch (Exception e) {
			resp.setStatusCode(1000);
			LOG.error(e.toString());
		}
		
		LOG.debug("End");
		return resp;
	}
	
	@POST
	@Path("/updateUser")
	@Secured("ROLE_ADMIN")
	public CommonCriteriaResp updateUser(PersistUserCriteriaReq req) {
		LOG.debug("Start");
		CommonCriteriaResp resp = new CommonCriteriaResp() {};
		
		try {
			LOG.debug(req);
			service.updateUser(req);			
		} catch (CustomerException cx) {
			resp.setStatusCode(cx.errCode);
			LOG.error(cx.toString());
		} catch (Exception e) {
			resp.setStatusCode(1000);
			LOG.error(e.toString());
		}
		
		LOG.debug("End");
		return resp;
	}
	
	@POST
	@Path("/updateProfile")
	public CommonCriteriaResp updateProfile(ProfileUpdateCriteriaReq req) {
		LOG.debug("Start");
		CommonCriteriaResp resp = new CommonCriteriaResp() {};
		
		try {
			LOG.debug(req);
			service.updateProfile(req);
		} catch (CustomerException cx) {
			resp.setStatusCode(cx.errCode);
			LOG.error(cx.toString());
		} catch (Exception e) {
			resp.setStatusCode(1000);
			LOG.error(e.toString());
		}
		
		LOG.debug("End");
		return resp;
	}

}
