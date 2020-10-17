package com.khodko.studyproject.services.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Component;

import com.khodko.studyproject.services.AgeCalculationService;

import lombok.Setter;

@Component
public class BasicAgeCalculationService implements AgeCalculationService {

	@Override
	public int calculateAge(Date dateOfBirth, Date currentTime) {
		if(dateOfBirth == null || currentTime == null) return -1;
		
		LocalDate birthDate = transformDate(dateOfBirth);
		
		LocalDate currentDate = transformDate(currentTime);
		
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
