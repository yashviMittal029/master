package com.spring.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.demo.service.IdGeneratorService;

/**
 * Handles requests for the Employee service.
 */
@Controller
public class NumberController {
	
	private static final Logger logger = LoggerFactory.getLogger(NumberController.class);
	
	@Autowired
	IdGeneratorService idGeneratorService;
	
	@RequestMapping(value = "/doIncrement")
	public @ResponseBody String IncrementValue() {
		
		logger.info("Entered in contoller");
		return "Result :: "+idGeneratorService.increaseNumberForID();
	}
	
}
