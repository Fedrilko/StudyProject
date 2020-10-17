package com.khodko.studyproject.services.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Component;

import com.khodko.studyproject.services.AgeCalculationService;

@Component
public class BasicAgeCalculationService implements AgeCalculationService {

	@Override
	public int calculateAge(Date dateOfBirth, Date current) {
		if(dateOfBirth == null || current == null) return -1;
		
		LocalDate birthDate = transformDate(dateOfBirth);
		
		LocalDate currentDate = transformDate(current);
		
		Period difference = Period.between(birthDate, currentDate);
		
		return difference.getYears();
	}
	
	private LocalDate transformDate(Date date) {
		
		String[] splitedDateComponents = date.toString().split("-");
		
		LocalDate localDate = LocalDate.of(Integer.valueOf(splitedDateComponents[0]),
				Integer.valueOf(splitedDateComponents[1]), Integer.valueOf(splitedDateComponents[2]));
		
		return localDate;
	}
	
}
