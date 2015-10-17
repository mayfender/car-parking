CREATE 
	TABLE vehicle_parking 
	( 
		id int NOT NULL, 
		in_date_time datetime, 
		out_date_time datetime, 
		price int, 
		status tinyint DEFAULT '0' NOT NULL, 
		license_no int NOT NULL, 
		PRIMARY KEY (id), 
		CONSTRAINT ix1 UNIQUE (license_no) 
	) 
	ENGINE=InnoDB DEFAULT CHARSET=utf8;

	
	
CREATE
    TABLE conf
    (
        conf_key VARCHAR(30),
        conf_value VARCHAR(100),
        PRIMARY KEY (conf_key)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8