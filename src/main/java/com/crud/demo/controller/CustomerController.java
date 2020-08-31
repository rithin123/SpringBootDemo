package com.crud.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.demo.model.CustomerDomain;
import com.crud.demo.service.CustomerService;






@RestController
@RequestMapping(path="/customer")
@CrossOrigin
public class CustomerController {
	
	@Autowired
	CustomerService customerservice;
	
	
	@GetMapping(path="/", produces = "application/json")
	public List<CustomerDomain> getAllCustomers() 
	{
		System.out.println("inside getAllCustomers:::::::");
		System.out.println("customerservice.getAllCustomerService() "+customerservice.getAllCustomerService());
		return customerservice.getAllCustomerService();
	}

	@PostMapping(path="/", consumes = "application/json", produces = "application/json")
	public CustomerDomain updateCustomer(@RequestBody CustomerDomain customer)
	{
		
		return customerservice.updateCustomerService(customer);
	}
//	
	
	@PutMapping(path="/")
	public void insertCustomer(@RequestBody CustomerDomain customer)
	{
		System.out.println("insertCustomer:::::::::");
		customerservice.insertCustomerService(customer);
	}
	
	@DeleteMapping(path="/{customernumber}", produces = "application/json")
	public void deleteCustomer(@PathVariable("customernumber") String number ) 
	{
		
		 
		 try {
				int y=Integer.parseInt(number);
				Optional<Integer> cust= Optional.of(customerservice.deleteCustomerService(y));
				
		    } catch (Exception ex) {
		    	throw new org.springframework.web.server.ResponseStatusException(
		                HttpStatus.UNAUTHORIZED, "Wrong Customer Number");
		    }
		 
	}
//	
	@GetMapping(path="/{customernumber}",produces = "application/json")
	public CustomerDomain getCustomer(@PathVariable("customernumber") String number) 
	{
		
	
		
		try {
			int y=Integer.parseInt(number);
			Optional<CustomerDomain> cust= Optional.ofNullable(customerservice.getCustomerService(y));
			if(!cust.get().isStatus())
			{
				throw new org.springframework.web.server.ResponseStatusException(
		                HttpStatus.UNAUTHORIZED, "Wrong Customer Number");
			}
			return cust.get();
	    } catch (Exception ex) {
	    	throw new org.springframework.web.server.ResponseStatusException(
	                HttpStatus.UNAUTHORIZED, "Wrong Customer Number");
	    }
		
		
		
		
		
	            
		
	}
//	
	

}
