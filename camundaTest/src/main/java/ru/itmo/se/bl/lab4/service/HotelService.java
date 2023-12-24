package ru.itmo.se.bl.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.se.bl.lab4.entity.Hotel;
import ru.itmo.se.bl.lab4.exception.HotelBookException;
import ru.itmo.se.bl.lab4.exception.HotelNotFoundException;
import ru.itmo.se.bl.lab4.repository.HotelRepository;

import java.util.List;
import java.util.Random;

@Service
public class HotelService {
	private final HotelRepository repo;

	@Autowired
	public HotelService(HotelRepository repo) {
		this.repo = repo;
	}

	public Hotel getById(Integer id) throws HotelNotFoundException {
		return repo.findById(id).orElseThrow(() -> new HotelNotFoundException(id));
	}
}
