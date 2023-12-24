package ru.itmo.se.bl.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.se.bl.lab4.entity.Travel;
import ru.itmo.se.bl.lab4.exception.TravelBookException;
import ru.itmo.se.bl.lab4.exception.TravelNotFoundException;
import ru.itmo.se.bl.lab4.repository.TravelRepository;

import java.util.List;
import java.util.Random;

@Service
public class TravelService {
	private final TravelRepository repo;

	@Autowired
	public TravelService(TravelRepository repo) {
		this.repo = repo;
	}
	
	public Travel getById(Integer id) throws TravelNotFoundException {
		return repo.findById(id).orElseThrow(() -> new TravelNotFoundException(id));
	}

	public List<Travel> getByEndCityId(Integer endCityId) {
		return repo.findByEndCityId(endCityId);
	}
}
