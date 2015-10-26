package com.may.ple.parking.center.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.may.ple.parking.center.criteria.VehicleSaveCriteriaReq;
import com.may.ple.parking.center.criteria.VehicleSearchCriteriaReq;
import com.may.ple.parking.center.criteria.VehicleSearchCriteriaResp;
import com.may.ple.parking.center.entity.VehicleParking;
import com.may.ple.parking.center.repository.VehicleParkingRepository;
import com.may.ple.parking.center.util.DateTimeUtil;

@Service
public class VehicleService {
	private static final Logger LOG = Logger.getLogger(VehicleService.class.getName());
	private VehicleParkingRepository vehicleParkingRepository;
	private DataSource dataSource;
	
	@Autowired
	public VehicleService(VehicleParkingRepository vehicleParkingRepository, DataSource dataSource) {
		this.vehicleParkingRepository = vehicleParkingRepository;
		this.dataSource = dataSource;
	}
	
	public VehicleSearchCriteriaResp findVehicleParking(VehicleSearchCriteriaReq req) throws Exception {
		VehicleSearchCriteriaResp resp = new VehicleSearchCriteriaResp();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select id, in_date_time, out_date_time, price, status, license_no, device_id, gate_name ");
			sql.append(" from vehicle_parking where 1=1 ");
			
			if(req != null) {
				if(req.getLicenseNo() != null) {
					sql.append(" and license_no like '%" + req.getLicenseNo() + "%' ");
				}
				if(!StringUtils.isBlank(req.getStartDate())) {
					sql.append(" and in_date_time >= STR_TO_DATE('" + req.getStartDate() + " 00:00:00','%d-%m-%Y %H:%i:%s') ");
				}
				if(!StringUtils.isBlank(req.getEndDate())) {
					sql.append(" and in_date_time <= STR_TO_DATE('" + req.getEndDate() + " 23:59:59','%d-%m-%Y %H:%i:%s') ");
				}
				if(req.getStatus() != null) {
					sql.append(" and status = " + req.getStatus());
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
				try { if(rst != null) rst.close(); } catch (Exception e2) {}
				try { if(pstmt != null) pstmt.close(); } catch (Exception e2) {}
			}
			
			
			//-----------------: Get data 
			sql.append(" order by in_date_time desc ");
			sql.append(" limit " + (req.getCurrentPage() - 1) * req.getItemsPerPage() + ", " + req.getItemsPerPage());
			
			pstmt = conn.prepareStatement(sql.toString());
			rst = pstmt.executeQuery();
			List<VehicleParking> vehicleParkings = new ArrayList<VehicleParking>();
			VehicleParking vehicleParking;
			
			while(rst.next()) {
				vehicleParking = new VehicleParking(rst.getTimestamp("in_date_time"), 
													rst.getTimestamp("out_date_time"), 
													rst.getInt("price"), 
													rst.getInt("status"), 
													rst.getString("license_no"), 
													rst.getString("device_id"), rst.getString("gate_name"));
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
			try { if(rst != null) rst.close(); } catch (Exception e2) {}
			try { if(pstmt != null) pstmt.close(); } catch (Exception e2) {}
			try { if(conn != null) conn.close(); } catch (Exception e2) {}
		}
	}
	
	public void saveVehicleParking(VehicleSaveCriteriaReq req) {
		try {
			VehicleParking vehicleParking = new VehicleParking(new Date(), null, null, 0, req.getLicenseNo(), req.getDeviceId(), req.getGateName());
			vehicleParkingRepository.save(vehicleParking);
		} catch (Exception e) {
			LOG.error(e.toString());
			throw e;
		}
	}
	
}
