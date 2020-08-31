package com.crud.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.crud.demo.model.CustomerDomain;
import com.crud.demo.repo.CustomerInfoRepo;

@Service
@CacheConfig(cacheNames = {"customer"})
public class CustomerService {
	
	@Autowired
	CustomerInfoRepo customerrepo;
	
	public CustomerDomain insertCustomerService(CustomerDomain customer) {
		//customerrepo.saveAndFlush(customer);
		//customerrepo.saveAndFlush(customer); save
		   return customerrepo.save(customer);
	}
	
	public CustomerDomain updateCustomerService(CustomerDomain customer) {
		System.out.println("inside of update::");
		
//		Optional<CustomerDomain> userFromDb = customerrepo.findBy(customer.getId());
//		    // crush the variables of the object found
//		    userFromDb.get().setName(customer.getName());
//		    userFromDb.get().setEmail_address(customer.getEmail_address());
//		    userFromDb.get().setPhone_number(customer.getPhone_number());
//		    userFromDb.get().setStatus(customer.isStatus());
//		    customerrepo.save(userFromDb.get());
//		
//		    return customerrepo.save(userFromDb.get());
		
		CustomerDomain userFromDb = customerrepo.findByNumber(customer.getId());
	    // crush the variables of the object found
	    userFromDb.setName(customer.getName());
	    userFromDb.setEmail_address(customer.getEmail_address());
	    userFromDb.setPhone_number(customer.getPhone_number());
	    userFromDb.setStatus(customer.isStatus());
	   // customerrepo.save(userFromDb);
	
	    return customerrepo.save(userFromDb);
		  //return customerrepo.updateCustomer(customer.getId(),customer.getName(),customer.getEmail_address(),customer.getPhone_number(),customer.isStatus());
	}
	
    public int deleteCustomerService(int number) {
    	return customerrepo.deleteByNumber(number);
	}
 
    @Cacheable
   public CustomerDomain getCustomerService(int number) {
	    return customerrepo.findByNumber(number);
	}
   
   @Cacheable
   public List<CustomerDomain> getAllCustomerService() {
	  
	   List<CustomerDomain> customers= new ArrayList<CustomerDomain>();
	   customerrepo.findAllByStatus(true).forEach(customers::add);
	   
	   return customers;
	}
}
