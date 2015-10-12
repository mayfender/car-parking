package com.may.ple.parking.center.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.may.ple.parking.center.constant.RolesConstant;
import com.may.ple.parking.center.criteria.PersistUserCriteriaReq;
import com.may.ple.parking.center.criteria.ProfileUpdateCriteriaReq;
import com.may.ple.parking.center.entity.Roles;
import com.may.ple.parking.center.entity.Users;
import com.may.ple.parking.center.exception.CustomerException;
import com.may.ple.parking.center.repository.UserRepository;

@Service
public class UserService {
	private static final Logger LOG = Logger.getLogger(UserService.class.getName());
	@Autowired
	private UserRepository userRepository;
	@Autowired	
	private PasswordEncoder passwordEncoder;
	
	public List<Users> findAllUser() {
		try {
			return userRepository.findAll(new Sort(Direction.ASC, "userName"));
		} catch (Exception e) {
			LOG.error(e.toString());
			throw e;
		}
	}
	
	public void saveUser(PersistUserCriteriaReq req) throws Exception {
		try {
			Users u = userRepository.findByUserName(req.getUserName());
			if(u != null) {
				throw new CustomerException(2000, "This username is existing");
			}
			
			String password = passwordEncoder.encode(new String(Base64.decode(req.getPassword().getBytes())));
			List<Roles> roles = getRole(req.getUserName(), req.getAuthority());
			Date currentDate = new Date();
			
			Users user = new Users(req.getUserName(), password, currentDate, currentDate, req.getStatus(), roles);
			userRepository.save(user);
		} catch (Exception e) {
			LOG.error(e.toString());
			throw e;
		}
	}
	
	public void updateUser(PersistUserCriteriaReq req) throws Exception {
		try {
			Users user = userRepository.findOne(req.getId());
			
			if(!user.getUserName().equals(req.getUserName())) {
				Users u = userRepository.findByUserName(req.getUserName());
				if(u != null)
					throw new CustomerException(2000, "This username is existing");
			}
			
			user.setUserName(req.getUserName());
			user.setEnabled(req.getStatus());
			user.setUpdatedDateTime(new Date());
			
			List<Roles> roles = getRole(req.getUserName(), req.getAuthority());
			Roles r = roles.get(0);
			
			Roles role = user.getRoles().get(0);
			role.setUserName(req.getUserName());
			role.setAuthority(r.getAuthority());
			role.setName(r.getName());
			
			userRepository.save(user);
		} catch (Exception e) {
			LOG.error(e.toString());
			throw e;
		}
	}
	
	public void deleteUser(long userId) throws Exception {
		try {
			userRepository.delete(userId);
		} catch (Exception e) {
			LOG.error(e.toString());
			throw e;
		}
	}
	
	public void updateProfile(ProfileUpdateCriteriaReq req) throws Exception {
		try {
			if(!req.getNewUserName().equals(req.getOldUserName())) {
				Users u = userRepository.findByUserName(req.getNewUserName());
				if(u != null)
					throw new CustomerException(2000, "This username is existing");	
			}
			
			Users user = userRepository.findByUserName(req.getOldUserName());
			user.setUserName(req.getNewUserName());
			user.setUpdatedDateTime(new Date());
			
			if(!StringUtils.isBlank(req.getPassword())) {
				String password = passwordEncoder.encode(new String(Base64.decode(req.getPassword().getBytes())));		
				user.setPassword(password);
			}
						
			userRepository.save(user);
		} catch (Exception e) {
			LOG.error(e.toString());
			throw e;
		}
	}
	
	private List<Roles> getRole(String userName, String authority) throws Exception {
		RolesConstant roleConstant = RolesConstant.valueOf(authority);
		
		if(roleConstant == null)
			throw new Exception("Not found Role from authority : " + authority);
		
		List<Roles> roles = new ArrayList<Roles>();
		Roles role = new Roles(userName, roleConstant.toString(), roleConstant.getName());
		roles.add(role);
		return roles;
	}
	
}
