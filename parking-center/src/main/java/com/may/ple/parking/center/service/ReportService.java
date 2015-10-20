package com.may.ple.parking.center.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
	private static final Logger LOG = Logger.getLogger(ReportService.class.getName());
	private DataSource dataSource;
	
	@Autowired
	public ReportService(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Map<String, List<String>> reportYear() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Map<String, List<String>> result = new HashMap<String, List<String>>();
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select count(id) as num, year(in_date_time) as year ");
			sql.append(" from vehicle_parking ");
			sql.append(" group by year(in_date_time) ");
			sql.append(" order by in_date_time asc ");
			
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rst = pstmt.executeQuery();
			List<String> years = new ArrayList<String>();
			List<String> values = new ArrayList<String>();
			
			while(rst.next()) {
				years.add(rst.getString("year"));
				values.add(rst.getString("num"));
			}
			result.put("years", years);
			result.put("values", values);
			
			return result;
		} catch (Exception e) {
			LOG.error(e.toString());
			throw e;
		} finally {
			try { if(rst != null) rst.close(); } catch (Exception e2) {}
			try { if(pstmt != null) pstmt.close(); } catch (Exception e2) {}
			try { if(conn != null) conn.close(); } catch (Exception e2) {}
		}
	}
	
	public Map<String, List<String>> reportMonth(String year) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Map<String, List<String>> result = new HashMap<String, List<String>>();
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select sum(month(in_date_time) = 01) as jan, ");
			sql.append(" sum(month(in_date_time) = 02) as feb, ");
			sql.append(" sum(month(in_date_time) = 03) as mar, ");
			sql.append(" sum(month(in_date_time) = 04) as apr, ");
			sql.append(" sum(month(in_date_time) = 05) as may, ");
			sql.append(" sum(month(in_date_time) = 06) as june, ");
			sql.append(" sum(month(in_date_time) = 07) as july, ");
			sql.append(" sum(month(in_date_time) = 08) as aug, ");
			sql.append(" sum(month(in_date_time) = 09) as sept, ");
			sql.append(" sum(month(in_date_time) = 10) as oct, ");
			sql.append(" sum(month(in_date_time) = 11) as nov, ");
			sql.append(" sum(month(in_date_time) = 12) as decem ");
			sql.append(" from vehicle_parking ");
			sql.append(" where year(in_date_time) = ?1 ");
			
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, year);
			rst = pstmt.executeQuery();
			List<String> years = new ArrayList<String>();
			List<String> values = new ArrayList<String>();
			
			while(rst.next()) {
				years.add(rst.getString("jan"));
				values.add(rst.getString("feb"));
			}
			result.put("years", years);
			result.put("values", values);
			
			return result;
		} catch (Exception e) {
			LOG.error(e.toString());
			throw e;
		} finally {
			try { if(rst != null) rst.close(); } catch (Exception e2) {}
			try { if(pstmt != null) pstmt.close(); } catch (Exception e2) {}
			try { if(conn != null) conn.close(); } catch (Exception e2) {}
		}
	}
	
}
