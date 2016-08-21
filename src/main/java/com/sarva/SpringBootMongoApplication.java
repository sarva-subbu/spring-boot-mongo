package com.sarva;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.Setter;

@SpringBootApplication
public class SpringBootMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMongoApplication.class, args);
	}
	
	@Bean
	CommandLineRunner loadCars(CarRepository carRepository) {
		return (args) -> {
			Arrays.asList("Corolla,Innova,Prius,Accord,Zen".split(","))
					.forEach(e -> carRepository.save(new Car(e)));
			System.out.println("Finding car by name: " + carRepository.findByCarName("Accord"));
		};
	}
	
}

@RestController
class CarController {
	
	@Autowired CarRepository carRepository;
	
	public CarController(CarRepository carRepository) {
		this.carRepository = carRepository;
	}
	
	@RequestMapping(value="/cars")
	public List<Car> getCars() {
		return carRepository.findAll();
	}
	
	@RequestMapping(value="/test")
	public String test() {
		return "hello...";
	}
	
}

@RepositoryRestResource
interface CarRepository extends JpaRepository<Car, Long> {
	
	Car findByCarName(@Param("carName") String carName);
}

@Entity
class Car {
	@Id
	@GeneratedValue
	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String carName;
	
	Car() {
		//
	}
	
	public Car(String carName) {
		this.carName = carName;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", carName=" + carName + "]";
	}
	
}