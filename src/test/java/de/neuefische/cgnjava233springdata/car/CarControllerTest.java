package de.neuefische.cgnjava233springdata.car;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

	private final static String BASE_URI = "/api/cars";

	@Autowired
	private MockMvc mockMvc; // Um HTTP-Requests zu simulieren
	@Autowired
	private ObjectMapper objectMapper; // Wandelt JSON in Java Objekte um und umgekehrt

	@Test
	@DirtiesContext
	void getAllCars_whenNoEntriesInDb_expectStatus200AndReturnEmptyListAsJson() throws Exception {
		mockMvc.perform(get(BASE_URI))
				.andExpect(status().isOk())
				.andExpect(content().json("[]"));
	}

	@Test
	@DirtiesContext
	void getAllCars_whenEntriesInDb_expectStatus200AndReturnEntriesAsJson() throws Exception {
		NewCar car = new NewCar("test", "test");
		String carAsJson = objectMapper.writeValueAsString(car);

		MvcResult result = mockMvc.perform(post(BASE_URI)
						.contentType(MediaType.APPLICATION_JSON)
						.content(carAsJson)
				)
				.andExpect(status().isOk())
				.andReturn();

		Car savedCar = objectMapper.readValue(result.getResponse().getContentAsString(), Car.class);

		List<Car> cars = List.of(savedCar);
		String carsAsJson = objectMapper.writeValueAsString(cars);

		mockMvc.perform(get(BASE_URI))
				.andExpect(status().isOk())
				.andExpect(content().json(carsAsJson));
	}

	@Test
	@DirtiesContext
	void getCarById() throws Exception {
		NewCar car = new NewCar("test", "test");
		String carAsJson = objectMapper.writeValueAsString(car);

		MvcResult result = mockMvc.perform(post(BASE_URI)
						.contentType(MediaType.APPLICATION_JSON)
						.content(carAsJson)
				)
				.andExpect(status().isOk())
				.andReturn();

		Car savedCar = objectMapper.readValue(result.getResponse().getContentAsString(), Car.class);
		String savedCarAsJson = objectMapper.writeValueAsString(savedCar);

		mockMvc.perform(get(BASE_URI + "/" + savedCar.id()))
				.andExpect(status().isOk())
				.andExpect(content().json(savedCarAsJson));
	}

	@Test
	@DirtiesContext
	void addCar() throws Exception{
		NewCar car = new NewCar("test", "test");
		String carAsJson = objectMapper.writeValueAsString(car);

		MvcResult result = mockMvc.perform(post(BASE_URI)
						.contentType(MediaType.APPLICATION_JSON)
						.content(carAsJson)
				)
				.andExpect(status().isOk())
				.andReturn();

		Car savedCar = objectMapper.readValue(result.getResponse().getContentAsString(), Car.class);

		assertNotNull(savedCar.id());
		assertNotNull(savedCar.brand());
		assertNotNull(savedCar.color());
	}

	@Test
	@DirtiesContext
	void deleteCarById() throws Exception {
		NewCar car = new NewCar("test", "test");
		String carAsJson = objectMapper.writeValueAsString(car);

		MvcResult result = mockMvc.perform(post(BASE_URI)
						.contentType(MediaType.APPLICATION_JSON)
						.content(carAsJson)
				)
				.andExpect(status().isOk())
				.andReturn();

		Car savedCar = objectMapper.readValue(result.getResponse().getContentAsString(), Car.class);

		mockMvc.perform(delete(BASE_URI + "/" + savedCar.id()))
				.andExpect(status().isOk());
	}
}