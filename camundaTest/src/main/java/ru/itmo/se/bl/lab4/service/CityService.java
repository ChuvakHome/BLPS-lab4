package ru.itmo.se.bl.lab4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.itmo.se.bl.lab4.entity.City;
import ru.itmo.se.bl.lab4.repository.CityRepository;

@Service
public class CityService {
	private final CityRepository repo;

	@Autowired
	public CityService(CityRepository repo) {
		this.repo = repo;
	}

	public List<City> getAll() {
		return repo.findAll();
	}
	
	public City getCityById(Integer id) {
		return repo.findById(id).orElse(null);
	}
	
	public List<City> getCitiesByName(String name) {
		return repo.findByName(name);
	}
	
	public List<City> getCitiesByCountryName(String countryName) {
		return repo.findAllByCountryName(countryName);
	}
}
