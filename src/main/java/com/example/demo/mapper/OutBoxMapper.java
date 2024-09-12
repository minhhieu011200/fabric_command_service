package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.domain.base.Entities;
import com.example.demo.domain.entities.Outbox;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class OutBoxMapper<T extends Entities> {

	public Outbox toEntity(String eventype,T t) {
		Outbox outbox=new Outbox();
		outbox.setEventType(eventype);
		outbox.setAggregateId(t.getId().toString());
		outbox.setIsProccessed(false);
		log.info("22222222222222222222222"+t);
		try {
			outbox.setPayload(new ObjectMapper().writeValueAsString(t));
		} catch (JsonProcessingException e) {
			log.error("2222222222222222222222222"+e);
			throw new AppException(ErrorCode.UNCATEGORIZED);
		}
		return outbox;
	}
	
}
