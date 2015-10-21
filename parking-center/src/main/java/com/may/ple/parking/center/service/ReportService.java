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
			sql.append(" where year(in_date_time) = ? ");
			
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, year);
			rst = pstmt.executeQuery();
			List<String> values = new ArrayList<String>();
			
			if(rst.next()) {
				values.add(rst.getString(1));
				values.add(rst.getString(2));
				values.add(rst.getString(3));
				values.add(rst.getString(4));
				values.add(rst.getString(5));
				values.add(rst.getString(6));
				values.add(rst.getString(7));
				values.add(rst.getString(8));
				values.add(rst.getString(9));
				values.add(rst.getString(10));
				values.add(rst.getString(11));
				values.add(rst.getString(12));
			}
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
	
	public Map<String, List<String>> reportDay(String year, String month) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Map<String, List<String>> result = new HashMap<String, List<String>>();
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select sum(day(in_date_time) = 01) as i, ");
			sql.append(" sum(day(in_date_time) = 02) as ii, ");
			sql.append(" sum(day(in_date_time) = 03) as iii, ");
			sql.append(" sum(day(in_date_time) = 04) as iv, ");
			sql.append(" sum(day(in_date_time) = 05) as v, ");
			sql.append(" sum(day(in_date_time) = 06) as vi, ");
			sql.append(" sum(day(in_date_time) = 07) as vii, ");
			sql.append(" sum(day(in_date_time) = 08) as viii, ");
			sql.append(" sum(day(in_date_time) = 09) as ix, ");
			sql.append(" sum(day(in_date_time) = 10) as x, ");
			sql.append(" sum(day(in_date_time) = 11) as xi, ");
			sql.append(" sum(day(in_date_time) = 12) as xii, ");
			sql.append(" sum(day(in_date_time) = 13) as xiii, ");
			sql.append(" sum(day(in_date_time) = 14) as xiv, ");
			sql.append(" sum(day(in_date_time) = 15) as xv, ");
			sql.append(" sum(day(in_date_time) = 16) as xvi, ");
			sql.append(" sum(day(in_date_time) = 17) as xvii, ");
			sql.append(" sum(day(in_date_time) = 18) as xviii, ");
			sql.append(" sum(day(in_date_time) = 19) as xix, ");
			sql.append(" sum(day(in_date_time) = 20) as xx, ");
			sql.append(" sum(day(in_date_time) = 21) as xxi, ");
			sql.append(" sum(day(in_date_time) = 22) as xxii, ");
			sql.append(" sum(day(in_date_time) = 23) as xxiii, ");
			sql.append(" sum(day(in_date_time) = 24) as xxiv, ");
			sql.append(" sum(day(in_date_time) = 25) as xxv, ");
			sql.append(" sum(day(in_date_time) = 26) as xxvi, ");
			sql.append(" sum(day(in_date_time) = 27) as xxvii, ");
			sql.append(" sum(day(in_date_time) = 28) as xxviii, ");
			sql.append(" sum(day(in_date_time) = 29) as xxix, ");
			sql.append(" sum(day(in_date_time) = 30) as xxx, ");
			sql.append(" sum(day(in_date_time) = 31) as xxxi ");
			sql.append(" from vehicle_parking ");
			sql.append(" where year(in_date_time) = ? and month(in_date_time) = ? ");
			
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, year);
			pstmt.setString(2, month);
			rst = pstmt.executeQuery();
			List<String> values = new ArrayList<String>();
			
			if(rst.next()) {
				values.add(rst.getString(1));
				values.add(rst.getString(2));
				values.add(rst.getString(3));
				values.add(rst.getString(4));
				values.add(rst.getString(5));
				values.add(rst.getString(6));
				values.add(rst.getString(7));
				values.add(rst.getString(8));
				values.add(rst.getString(9));
				values.add(rst.getString(10));
				values.add(rst.getString(11));
				values.add(rst.getString(12));
				values.add(rst.getString(13));
				values.add(rst.getString(14));
				values.add(rst.getString(15));
				values.add(rst.getString(16));
				values.add(rst.getString(17));
				values.add(rst.getString(18));
				values.add(rst.getString(19));
				values.add(rst.getString(20));
				values.add(rst.getString(21));
				values.add(rst.getString(22));
				values.add(rst.getString(23));
				values.add(rst.getString(24));
				values.add(rst.getString(25));
				values.add(rst.getString(26));
				values.add(rst.getString(27));
				values.add(rst.getString(28));
				values.add(rst.getString(29));
				values.add(rst.getString(30));
				values.add(rst.getString(31));
			}
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
