package ru.itmo.se.bl.lab4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.itmo.se.bl.lab4.entity.Country;
import ru.itmo.se.bl.lab4.repository.CountryRepository;

@Service
public class CountryService {
	private final CountryRepository repo;

	@Autowired
	public CountryService(CountryRepository repo) {
		this.repo = repo;
	}
	
	public List<Country> getAll() {
		return repo.findAll();
	}

	public Country getCountryById(Integer id) {
		return repo.findById(id).orElse(null);
	}

	public Country getCountryByName(String name) {
		return repo.findByName(name).orElse(null);
	}
}
