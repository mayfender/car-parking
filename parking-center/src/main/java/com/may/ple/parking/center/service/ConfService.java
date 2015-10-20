package com.may.ple.parking.center.service;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.may.ple.parking.center.criteria.SettingUpdateCriteriaReq;
import com.may.ple.parking.center.custom.PropertiesCustom;
import com.may.ple.parking.center.repository.ConfRepository;

@Service
public class ConfService {
	private static final Logger LOG = Logger.getLogger(ConfService.class.getName());
	private ConfRepository confRepository;
	private PropertiesCustom dbProp;
	
	@Autowired	
	public ConfService(ConfRepository confRepository, PropertiesCustom dbProp) {
		this.confRepository = confRepository;
		this.dbProp = dbProp;
	}
	
	@Transactional
	public void updateSetting(SettingUpdateCriteriaReq req) {
		try {
			String afterPriceRate = String.valueOf(req.getAfterPriceRate());
			String beforeHour = String.valueOf(req.getBeforeHour());
			String beforeHourPriceRate = String.valueOf(req.getBeforeHourPriceRate());
			String minuteToHour = String.valueOf(req.getMinuteToHour());
			String pricePerTime = String.valueOf(req.getPricePerTime());
			String unlimtedTime = String.valueOf(req.getUnlimtedTime());
			String parkingSize = String.valueOf(req.getParkingSize());
			
			confRepository.findOne("after.pricerate").setConfValue(afterPriceRate);
			confRepository.findOne("before.hour").setConfValue(beforeHour);
			confRepository.findOne("before.hour.pricerate").setConfValue(beforeHourPriceRate);
			confRepository.findOne("minute.to.hour").setConfValue(minuteToHour);
			confRepository.findOne("price.per.time").setConfValue(pricePerTime);
			confRepository.findOne("unlimted.time").setConfValue(unlimtedTime);
			confRepository.findOne("parking.size").setConfValue(parkingSize);
			
			dbProp.put("after.pricerate", afterPriceRate);
			dbProp.put("before.hour", beforeHour);
			dbProp.put("before.hour.pricerate", beforeHourPriceRate);
			dbProp.put("minute.to.hour", minuteToHour);
			dbProp.put("price.per.time", pricePerTime);
			dbProp.put("unlimted.time", unlimtedTime);
			dbProp.put("parking.size", parkingSize);
		} catch (Exception e) {
			LOG.error(e.toString());
			throw e;
		}
	}
	
}
