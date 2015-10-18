package com.may.ple.parking.center.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.may.ple.parking.center.entity.Conf;

public interface ConfRepository extends JpaRepository<Conf, String> {
	
}
