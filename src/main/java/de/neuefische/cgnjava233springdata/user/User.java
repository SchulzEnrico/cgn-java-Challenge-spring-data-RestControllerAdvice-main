package de.neuefische.cgnjava233springdata.user;

import de.neuefische.cgnjava233springdata.car.Car;
import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("users")
public record User(
		String id,
		@With String username,
		@With String password,
		@With String email,
		List<Car> cars
) {
}
