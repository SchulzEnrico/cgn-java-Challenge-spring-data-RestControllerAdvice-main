package de.neuefische.cgnjava233springdata.car;

import de.neuefische.cgnjava233springdata.car.exception.CarAlreadyExistException;
import de.neuefische.cgnjava233springdata.car.exception.CarNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarService {

	private final CarRepository carRepository;

	public Car addCar(NewCar car) {
		if(!carRepository.findCarByBrand(car.brand()).isEmpty()){
			throw new CarAlreadyExistException("Car with brand " + car.brand() + " already Exist");
		}
		Car carToSave = new Car(
				UUID.randomUUID().toString(),
				car.brand(),
				car.color()
		);
		return carRepository.save(carToSave);
	}

	public List<Car> getAllCars(){
		return carRepository.findAll();
	}

	public Car getCarById(String id){
		return carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("Car not found"));
	}

	public Car getCarByBrand(String brand){
		return carRepository.findCarByBrand(brand).orElseThrow(() -> new CarNotFoundException("Car not found"));
	}

	public Car getCarByIdOrBrand(String s){
		return carRepository.findCarByIdOrBrand(s).orElseThrow(() -> new CarNotFoundException("Car not found"));
	}

	public Car updateCar(Car car){
		return carRepository.save(car);
	}

	public void deleteCarById(String id){
		carRepository.deleteById(id);
	}

}
