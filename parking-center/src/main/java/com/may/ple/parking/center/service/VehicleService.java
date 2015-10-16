package com.may.ple.parking.center.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.may.ple.parking.center.criteria.VehicleCriteriaReq;
import com.may.ple.parking.center.criteria.VehicleCriteriaResp;
import com.may.ple.parking.center.entity.Vehicle;
import com.may.ple.parking.center.entity.VehicleParking;
import com.may.ple.parking.center.repository.VehicleParkingRepository;
import com.may.ple.parking.center.util.DateTimeUtil;

@Service
public class VehicleService {
	private static final Logger LOG = Logger.getLogger(VehicleService.class.getName());
//	private VehicleParkingRepository vehicleParkingRepository;
	private DataSource dataSource;
	
	@Autowired
	public VehicleService(VehicleParkingRepository vehicleParkingRepository, DataSource dataSource) {
//		this.vehicleParkingRepository = vehicleParkingRepository;
		this.dataSource = dataSource;
	}
	
	public VehicleCriteriaResp findVehicleParking(VehicleCriteriaReq req) throws Exception {
		VehicleCriteriaResp resp = new VehicleCriteriaResp();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select vp.id, vp.in_date_time, vp.out_date_time, vp.price, vp.status, v.license_no ");
			sql.append(" from vehicle_parking vp join vehicle v on vp.vehicle_id = v.id where 1=1 ");
			
			if(req != null) {
				if(!StringUtils.isBlank(req.getLicenseNo())) {
					sql.append(" and v.license_no like '%" + req.getLicenseNo() + "%'");
				}
				if(!StringUtils.isBlank(req.getStartDate())) {
					sql.append(" and in_date_time >= STR_TO_DATE('" + req.getStartDate() + " 00:00:00','%d-%m-%Y %H:%i:%s') ");
				}
				if(!StringUtils.isBlank(req.getEndDate())) {
					sql.append(" and in_date_time <= STR_TO_DATE('" + req.getEndDate() + " 23:59:59','%d-%m-%Y %H:%i:%s') ");
				}
				if(req.getStatus() != null) {
					sql.append(" and vp.status = " + req.getStatus());
				}
			}
			conn = dataSource.getConnection();
			
			//-----------------: Get size
			try {				
				StringBuilder sqlCount = new StringBuilder();
				sqlCount.append("select count(*) as size from ( " + sql.toString()+ " ) sub");
				pstmt = conn.prepareStatement(sqlCount.toString());
				rst = pstmt.executeQuery();
				if(rst.next()) {
					resp.setTotalItems(rst.getLong("size"));
				}
			} catch (Exception e) {
				LOG.error(e.toString());
				throw e;
			} finally {
				try { rst.close(); } catch (Exception e2) {}
				try { pstmt.close(); } catch (Exception e2) {}
			}
			
			
			//-----------------: Get data 
			sql.append(" order by vp.in_date_time desc ");
			sql.append(" limit " + (req.getCurrentPage() - 1) * req.getItemsPerPage() + ", " + req.getItemsPerPage());
			
			pstmt = conn.prepareStatement(sql.toString());
			rst = pstmt.executeQuery();
			List<VehicleParking> vehicleParkings = new ArrayList<VehicleParking>();
			VehicleParking vehicleParking;
			Vehicle vehicle;
			
			while(rst.next()) {
				vehicle = new Vehicle(rst.getString("license_no"), null);
				vehicleParking = new VehicleParking(rst.getTimestamp("in_date_time"), 
													rst.getTimestamp("out_date_time"), 
													rst.getInt("price"), 
													rst.getInt("status"), 
													vehicle);
				vehicleParking.setId(rst.getLong("id"));
				
				if(vehicleParking.getInDateTime() != null && vehicleParking.getOutDateTime() != null) {					
					vehicleParking.setDateTimeDiffMap(DateTimeUtil.dateTimeDiff(vehicleParking.getInDateTime(), vehicleParking.getOutDateTime()));					
				}
				
				vehicleParkings.add(vehicleParking);
			}
			resp.setVehicleParkings(vehicleParkings);
			
			return resp;
		} catch (Exception e) {
			LOG.error(e.toString());
			throw e;
		} finally {
			try { rst.close(); } catch (Exception e2) {}
			try { pstmt.close(); } catch (Exception e2) {}
			try { conn.close(); } catch (Exception e2) {}
		}
	}
	
}
