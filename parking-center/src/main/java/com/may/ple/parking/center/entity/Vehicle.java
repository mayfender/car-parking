package com.may.ple.parking.center.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
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
	@Temporal(TemporalType.TIMESTAMP)
	private Date inDateTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date outDateTime;
	@Column(nullable=false)
	private String licensePlate;
	private Integer price;
	private Integer status;
	
	protected Vehicle(){}
	
	public Vehicle(Date inDateTime, Date outDateTime, String licensePlate, Integer price, Integer status) {
		this.inDateTime = inDateTime;
		this.outDateTime = outDateTime;
		this.licensePlate = licensePlate;
		this.price = price;
		this.status = status;
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
	public Date getInDateTime() {
		return inDateTime;
	}
	public void setInDateTime(Date inDateTime) {
		this.inDateTime = inDateTime;
	}
	public Date getOutDateTime() {
		return outDateTime;
	}
	public void setOuDateTime(Date ouDateTime) {
		this.outDateTime = ouDateTime;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
