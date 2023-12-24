package ru.itmo.se.bl.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.se.bl.lab4.entity.InternationalPassport;
import ru.itmo.se.bl.lab4.repository.InternationalPassportRepository;

import java.util.List;

@Service
public class InternationalPassportService {
	private final InternationalPassportRepository repo;

	@Autowired
	public InternationalPassportService(InternationalPassportRepository repo) {
		this.repo = repo;
	}
	
	public InternationalPassport getById(Integer id) {
		return repo.findById(id).orElse(null);
	}

	public InternationalPassport save(InternationalPassport internationalPassport) {
		return repo.save(internationalPassport);
	}

	public void saveAll(List<InternationalPassport> internationalPassports) {
		repo.saveAll(internationalPassports);
	}

	public InternationalPassport getBySeriesAndNumber(String series, String number) {
		return repo.findBySeriesAndNumber(series, number).orElse(null);
	}
}
