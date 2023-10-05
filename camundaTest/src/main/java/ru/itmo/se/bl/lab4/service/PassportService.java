package ru.itmo.se.bl.lab4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.itmo.se.bl.lab4.entity.Passport;
import ru.itmo.se.bl.lab4.repository.PassportRepository;

@Service
public class PassportService {
	@Autowired
	private PassportRepository repo;
	
	public Passport getById(Integer id) {		
		return repo.findById(id).orElse(null);
	}
	
	public Passport save(Passport passport) {
		return repo.save(passport);
	}
	
	public Passport getBySeriesAndNumber(String series, String number) {
		return repo.findBySeriesAndNumber(series, number).orElse(null);
	}
}
