package com.may.ple.parking.center;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.may.ple.parking.center.custom.PropertiesCustom;

@Configuration
public class Conf {
	private static final Logger LOG = Logger.getLogger(Conf.class.getName());
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public PropertiesCustom dbProp() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rst = null;
		
		try {
			LOG.debug(":---------: Start load conf data :---------:");
			
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			rst = stmt.executeQuery("select conf_key, conf_value from conf");
			PropertiesCustom properties = new PropertiesCustom();
			
			while(rst.next()) {
				properties.put(rst.getString("conf_key"), rst.getString("conf_value"));
			}
			
			LOG.debug(":---------: Finished load conf data :---------:");
			return properties;
		} catch (Exception e) {
			LOG.error(e.toString());
			throw e;
		} finally {
			try { rst.close(); } catch (Exception e2) {}
			try { stmt.close(); } catch (Exception e2) {}
			try { conn.close(); } catch (Exception e2) {}
		}		
	}

}