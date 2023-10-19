package de.neuefische.cgnjava233springdata.car;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CarServiceTest {

	private CarRepository carRepository = mock(CarRepository.class);
	private CarService carService = new CarService(carRepository);

	@Test
	void addCar() {
		// GIVEN
		NewCar newCarToSave = new NewCar("test", "test");
		Car expected = new Car("1", "test", "test");
		// WHEN
		when(carRepository.save(any(Car.class))).thenReturn(expected);
		Car actual = carService.addCar(newCarToSave);
		// THEN
		verify(carRepository).save(any(Car.class));
		assertEquals(expected, actual);
	}

	@Test
	void getAllCars() {
		// GIVEN
		List<Car> expected = List.of(
				new Car("1", "test", "test"),
				new Car("2", "test", "test")
		);
		// WHEN
		when(carRepository.findAll()).thenReturn(expected);
		List<Car> actual = carService.getAllCars();
		// THEN
		verify(carRepository).findAll();
		assertEquals(expected, actual);
	}

	@Test
	void getCarById() {
		// GIVEN
		Car expected = new Car("1", "test", "test");
		// WHEN
		when(carRepository.findById(expected.id())).thenReturn(Optional.of(expected));
		Car actual = carService.getCarById(expected.id());
		// THEN
		verify(carRepository).findById(expected.id());
		assertEquals(expected, actual);
	}

	@Test
	void updateCar() {
		// GIVEN
		Car expected = new Car("1", "test", "test");
		// WHEN
		when(carRepository.save(expected)).thenReturn(expected);
		Car actual = carService.updateCar(expected);
		// THEN
		verify(carRepository).save(expected);
		assertEquals(expected, actual);
	}
}