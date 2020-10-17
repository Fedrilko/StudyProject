package com.khodko.studyproject.services.impl;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.khodko.studyproject.services.AgeCalculationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContextTest.xml"})
public class BasicAgeCalculationServiceTest {
	
	@Autowired
	private AgeCalculationService service;

	@Test
	public void calculateAge_shouldReturnCalculatedAge() {
		Date dateOfBirth = Date.valueOf("1989-10-16");
		Date arbitraryDate = Date.valueOf("2020-10-17");
		int actualAge = service.calculateAge(dateOfBirth, arbitraryDate);
		final int expectedAge = 31;
		assertEquals(expectedAge, actualAge);
	}
	
	@Test
	public void calculateAge_shouldReturnMinusOneIfBirthDateIsNull() {
		int actualValue = service.calculateAge(null, new Date(0));
		assertEquals(-1, actualValue);
	}
	
	@Test
	public void calculateAge_shouldReturnMinusOneIfCurrentDateIsNull() {
		int actualValue = service.calculateAge(new Date(0), null);
		assertEquals(-1, actualValue);
	}


}
