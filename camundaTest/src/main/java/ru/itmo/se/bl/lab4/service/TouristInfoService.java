package ru.itmo.se.bl.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.itmo.se.bl.lab4.entity.TouristInfo;
import ru.itmo.se.bl.lab4.repository.TouristInfoRepository;

@Service
public class TouristInfoService {
	@Autowired
	private TouristInfoRepository repo;
	
	public TouristInfo getById(Integer id) {
		return repo.findById(id).orElse(null);
	}
	
	public TouristInfo getByPassportId(Integer passportId) {		
		return repo.findByPassportId(passportId).orElse(null);
	}
	
	public TouristInfo getByInternationalPassportId(Integer internationalPassportId) {
		return repo.findByInternationalPassportId(internationalPassportId).orElse(null);
	}

	public TouristInfo save(TouristInfo touristInfo) {
		return repo.save(touristInfo);
	}
}
