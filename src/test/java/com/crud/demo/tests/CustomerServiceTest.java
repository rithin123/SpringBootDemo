package com.crud.demo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.mockito.AdditionalMatchers;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crud.demo.model.CustomerDomain;
import com.crud.demo.repo.CustomerInfoRepo;
import com.crud.demo.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerServiceTest {
	
	@Mock
	CustomerInfoRepo custRepo;
	
	@InjectMocks
	CustomerService custService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void inserTest()
	{
		CustomerDomain custMockreturn = new CustomerDomain();
		//CustomerInfoRepo
    	CustomerDomain custMock = new CustomerDomain();
    	custMock.setName("Ravi");
    	custMock.setEmail_address("Ravi@tataelxsi.com");
    	custMock.setPhone_number("9447919911");
    	custMock.setStatus(true);
    	
    	 when(custRepo.save(custMock)).thenReturn(custMockreturn);
        CustomerDomain response = custService.insertCustomerService(custMock);
         
        assertEquals(response.getName(),custMockreturn.getName());
 
	}
	
	@Test
	public void deleteTest()
	{
		CustomerDomain custMockreturn = new CustomerDomain();
		//CustomerInfoRepo
    	CustomerDomain custMock = new CustomerDomain();
    	custMock.setId(1);
//    	custMock.setEmail_address("Ravi@tataelxsi.com");
//    	custMock.setPhone_number("9447919911");
//    	custMock.setStatus(true);
    	int back=0;
    	 when(custRepo.deleteByNumber(custMock.getId())).thenReturn(back);
        int response = custService.deleteCustomerService(1);
         
        assertEquals(response,back);
 
	}
	
//	@Test
//	public void updateTest()
//	{
//		CustomerDomain custMockreturn = new CustomerDomain();
//		//CustomerInfoRepo
//    	CustomerDomain custMock = new CustomerDomain();
//    	custMock.setId(2);
//    	custMock.setName("Ravi");
//    	custMock.setEmail_address("Ravi@tataelxsi.com");
//    	custMock.setPhone_number("9447919911");
//    	custMock.setStatus(true);
//    	custRepo.save(custMock);
//    	
//    	custMock.setName("Ravi Misra");
//    	
//    	 when(custRepo.save(custMock)).thenReturn(new Answer<CustomerDomain>() {
//    		 
//    	 });
//    	 System.out.println(custMockreturn);
//    	 custMockreturn.setName("Ravi Mishra");
//        CustomerDomain response = custService.updateCustomerService(custMock);
//        System.out.println(response);
//        assertEquals(response.getId(),custMockreturn.getId());
// 
//	}

}
