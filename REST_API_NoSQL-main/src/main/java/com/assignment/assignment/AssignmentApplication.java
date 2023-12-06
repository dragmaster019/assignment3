package com.assignment.assignment;

import com.assignment.assignment.model.Employee;
import com.assignment.assignment.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class AssignmentApplication implements CommandLineRunner {

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		employeeRepository.deleteAll();

		employeeRepository.save(new Employee( UUID.randomUUID().toString(), "kumar", 78721298, "kumar@gmail.com", null, "https://a.com"));
		employeeRepository.save(new Employee( UUID.randomUUID().toString(), "sarthak", 787212980, "sarthak@gmail.com", null, "https://b.com"));
		employeeRepository.save(new Employee( UUID.randomUUID().toString(), "rajat", 787212980, "rajat@gmail.com", null, "https://c.com"));
		employeeRepository.save(new Employee( UUID.randomUUID().toString(), "ambuj", 787212980, "ambuj@gmail.com", null, "https://d.com"));
		employeeRepository.save(new Employee( UUID.randomUUID().toString(), "naman", 787212980, "naman@gmail.com", null, "https://e.com"));

		System.out.println("Data saved!");
	}
}
