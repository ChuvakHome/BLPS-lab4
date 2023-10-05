package ru.itmo.se.bl.lab4.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.itmo.se.bl.lab4.entity.InternationalPassport;

@Repository
public interface InternationalPassportRepository extends JpaRepository<InternationalPassport, Integer> {
	Optional<InternationalPassport> findBySeriesAndNumber(String series, String number);
}
