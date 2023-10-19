package de.neuefische.cgnjava233springdata.car;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends MongoRepository<Car, String> {
	Optional<Car> findCarByBrand(String brand);

	Optional<Car> findCarByIdOrBrand(String s);
}
