CREATE
    TABLE parking_db.vehicle
    (
        id INT NOT NULL,
        in_date_time DATETIME,
        out_date_time DATETIME,
        license_plate VARCHAR(30) NOT NULL,
        price INT,
    	status TINYINT DEFAULT '1',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8