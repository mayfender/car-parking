package com.may.ple.parking.center.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;

@NamedQueries({ 
	@NamedQuery(name = "Vehicle.findVehicle", 
			query = "select v from Vehicle v where u.enabled = ?1 order by u.userName asc") 
})
@Entity
public class Vehicle implements Serializable {
	private static final long serialVersionUID = -4271705333706537294L;
	@Id
	@TableGenerator(name="vehicleId", pkColumnValue="vehicle.id", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="vehicleId")
	private Long id;

}
