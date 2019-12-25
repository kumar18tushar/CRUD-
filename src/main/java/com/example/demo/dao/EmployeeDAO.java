package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeDAO {
	
	@Autowired
	EmployeeRepository employeeRepository;

	
	/*save an employee*/
	public Employee save(Employee emp){
		return employeeRepository.save(emp);
	}
	
	
	/*Search all employee*/
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}
	
	
	/*get employee by id*/
	public Employee findOne(Long empid) {
		Optional<Employee> optionalemployee= employeeRepository.findById(empid);
		return optionalemployee.get();
	}
	
	
	/*delete an employee*/
	public void delete(Employee emp) {
		employeeRepository.delete(emp);
	}

}