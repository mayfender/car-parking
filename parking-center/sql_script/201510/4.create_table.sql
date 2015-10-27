CREATE 
	TABLE vehicle_parking 
	( 
		id int NOT NULL, 
		in_date_time datetime, 
		out_date_time datetime, 
		price int, 
		status tinyint DEFAULT '0' NOT NULL, 
		license_no VARCHAR(4) NOT NULL, 
		device_id VARCHAR(100),
		gate_name VARCHAR(100),
		PRIMARY KEY (id)
	) 
	ENGINE=InnoDB DEFAULT CHARSET=utf8;

	
	
CREATE
    TABLE conf
    (
        conf_key VARCHAR(30),
        conf_value VARCHAR(100),
        PRIMARY KEY (conf_key)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
insert into conf (conf_key, conf_value) values ('after.pricerate', '30');
insert into conf (conf_key, conf_value) values ('before.hour', '1');
insert into conf (conf_key, conf_value) values ('before.hour.pricerate', '10');
insert into conf (conf_key, conf_value) values ('minute.to.hour', '1');
insert into conf (conf_key, conf_value) values ('price.per.time', '20');
insert into conf (conf_key, conf_value) values ('unlimted.time', 'false');
insert into conf (conf_key, conf_value) values ('parking.size', '-1');