package ru.itmo.se.bl.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.se.bl.lab4.entity.Passport;
import ru.itmo.se.bl.lab4.repository.PassportRepository;

@Service
public class PassportService {
	private final PassportRepository repo;

	@Autowired
	public PassportService(PassportRepository repo) {
		this.repo = repo;
	}
	
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
