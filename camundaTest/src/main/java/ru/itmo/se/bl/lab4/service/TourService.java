package ru.itmo.se.bl.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.se.bl.lab4.entity.Tour;
import ru.itmo.se.bl.lab4.repository.TourRepository;

import java.util.List;

@Service
public class TourService {
	private final TourRepository repo;

	@Autowired
	public TourService(TourRepository repo) {
		this.repo = repo;
	}

	public List<Tour> getByTourCityId(Integer tourCityId) {
		return repo.findByTourCityId(tourCityId);
	}
	
	public Tour getById(Integer id) {
		return repo.findById(id).orElse(null);
	}
}
