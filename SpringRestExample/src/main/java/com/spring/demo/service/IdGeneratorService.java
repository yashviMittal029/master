package com.spring.demo.service;

import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.demo.dao.IdGeneratorDao;

@Service
public class IdGeneratorService {

	@Autowired
	IdGeneratorDao idGeneratorDao;

	private static final Logger logger = LoggerFactory.getLogger(IdGeneratorService.class);
	
	public int increaseNumberForID() {
		
		int result = 0;
		try {
		result=	idGeneratorDao.doIncrementWithLock();
		logger.info("data saved....");
		}catch (StaleObjectStateException e) {
			logger.info("************** race condition occured, try again for perform this. *******************");
		}catch (Exception e) {
			throw e;
		}
		
		return result;
		
	}
	
	
}

