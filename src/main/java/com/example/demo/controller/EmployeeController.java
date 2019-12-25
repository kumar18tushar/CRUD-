package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.model.Employee;

@RestController
@RequestMapping("/company")

public class EmployeeController {

	@Autowired
	EmployeeDAO employeeDAO;
	
	
	/*To save an employee into database through url.So we have defined the crud functions in our dao . So dao will be called*/
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee emp) {
		return employeeDAO.save(emp);
	}
	
	
	
	/*Get all employees*/
	@GetMapping("/employees")
	public List<Employee>getAllEmployees(){
		return employeeDAO.findAll();
	}
	
	
	
	/*Get employee by a particular id*/
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeByID(@PathVariable(value="id") Long empid) {
		Employee emp = employeeDAO.findOne(empid);
		
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(emp);
	}
	
	
	
	/*Update an employee*/
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") Long empid , @Valid @RequestBody Employee empDetails)
	{
		Employee emp = employeeDAO.findOne(empid);
		
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}
		
		emp.setName(empDetails.getName());
		emp.setDesignation(empDetails.getDesignation());
		emp.setExperties(empDetails.getExperties());
		
		Employee updatedemployee=employeeDAO.save(emp);
		return ResponseEntity.ok().body(updatedemployee);
	}
	
	
	
	
	/*Delete an Employee*/
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Employee> DeleteEmployee(@PathVariable(value="id") Long empid){
		
		Employee emp = employeeDAO.findOne(empid);
		
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}
		
		employeeDAO.delete(emp);
		return ResponseEntity.ok().build();
	}
	
}
