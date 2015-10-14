CREATE 
	TABLE vehicle 
	( 
		id int NOT NULL, 
		license_no varchar(30) NOT NULL, 
		created_date_time datetime NOT NULL, 
		PRIMARY KEY (id) 
	) 
	ENGINE=InnoDB DEFAULT CHARSET=utf8;

	
	
CREATE 
	TABLE vehicle_parking 
	( 
		id int NOT NULL, 
		in_date_time datetime, 
		out_date_time datetime, 
		price int, 
		status tinyint DEFAULT '0' NOT NULL, 
		vehicle_id int NOT NULL, 
		PRIMARY KEY (id), 
		FOREIGN KEY (vehicle_id) 
		REFERENCES vehicle (id), 
		INDEX vehicle_id (vehicle_id) 
	)
	ENGINE=InnoDB DEFAULT CHARSET=utf8;