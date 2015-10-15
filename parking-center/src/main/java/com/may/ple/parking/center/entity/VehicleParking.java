package com.may.ple.parking.center.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@NamedQueries({ 
	@NamedQuery(name = "VehicleParking.findVehicleParking", 
			query = "select vp from VehicleParking vp where vp.status = ?1") 
})
@Entity
public class VehicleParking implements Serializable {
	private static final long serialVersionUID = -5313864241529415022L;
	@Id
	@TableGenerator(name="vehicleParkingId", pkColumnValue="vehicle_parking.id", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="vehicleParkingId")
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date inDateTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date outDateTime;
	private Integer price;
	private Integer status;
	@ManyToOne
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle;
	@Transient
	private Map<String, Long> dateTimeDiffMap;
	
	protected VehicleParking(){}

	public VehicleParking(Date inDateTime, Date outDateTime, Integer price, Integer status, Vehicle vehicle) {
		this.inDateTime = inDateTime;
		this.outDateTime = outDateTime;
		this.price = price;
		this.status = status;
		this.vehicle = vehicle;
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

	public void setOutDateTime(Date outDateTime) {
		this.outDateTime = outDateTime;
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

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Map<String, Long> getDateTimeDiffMap() {
		return dateTimeDiffMap;
	}

	public void setDateTimeDiffMap(Map<String, Long> dateTimeDiffMap) {
		this.dateTimeDiffMap = dateTimeDiffMap;
	}

}
