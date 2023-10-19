package de.neuefische.cgnjava233springdata.user;

import de.neuefische.cgnjava233springdata.car.Car;

import java.util.List;

public record UserResponse(
		String id,
		String username,
		List<Car> cars
) {
}
