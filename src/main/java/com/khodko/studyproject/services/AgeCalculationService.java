package com.khodko.studyproject.services;

import java.sql.Date;
import java.time.LocalDate;

public interface AgeCalculationService {
	int calculateAge(Date dateOfBirth, Date current);
}
