package com.sarva;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel="emp", path="employee")
public interface EmployeeRepository extends MongoRepository<Employee, String>{
	
	@RestResource(path="/findEmployeeByFirstName")
	List<Employee> findByFirstName(@Param("firstName") String firstName);

	@RestResource(path="/findEmployeeByLastName")
	List<Employee> findByLastName(@Param("lastName") String lastName);
	
	@RestResource(path="/findEmployeesWithFirstNameStartsWith")
	List<Employee> findByFirstNameRegex(@Param("firstName") String firstName);
	
}
