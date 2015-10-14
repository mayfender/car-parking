package com.may.ple.parking.center.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@NamedQueries({ 
	@NamedQuery(name = "Vehicle.findVehicle", 
			query = "select v from Vehicle v where v.id != ?1") 
})
@Entity
public class Vehicle implements Serializable {
	private static final long serialVersionUID = -4271705333706537294L;
	@Id
	@TableGenerator(name="vehicleId", pkColumnValue="vehicle.id", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="vehicleId")
	private Long id;
	private String licenseNo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDateTime;
	
	protected Vehicle(){}
	
	public Vehicle(String licenseNo, Date createdDateTime) {
		this.licenseNo = licenseNo;
		this.createdDateTime = createdDateTime;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	@JsonIgnore
	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	@JsonProperty
	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

}
