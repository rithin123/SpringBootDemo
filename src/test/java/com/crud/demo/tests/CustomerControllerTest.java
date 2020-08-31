package com.crud.demo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.crud.demo.controller.CustomerController;
import com.crud.demo.model.CustomerDomain;
import com.crud.demo.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
//@SpringBootTest()
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()//(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration
@WebAppConfiguration
public class CustomerControllerTest {
	
	
	@Mock
    private CustomerService custService;

    @InjectMocks
    private CustomerController custController;
	
	// bind the above RANDOM_PORT
//    @LocalServerPort
//    private int port;

    @Autowired
    private MockMvc mockMvc;
    
    
    @Autowired
	private WebApplicationContext context;
//    @Autowired
//    private TestRestTemplate restTemplate;
 
    
    @Before
    public void setUp() throws Exception {
    	//MockMvcBuilders.webAppContextSetup(context)
//        mockMvc = MockMvcBuilders.standaloneSetup(custController)
//        		.apply(springSecurity())
//                .build();
        mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
        
        
    }
//    
////    @Test
//    public void testHelloWorld() throws Exception {
//
//        when(custService.getCustomerService(1)).thenReturn(value);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/customer/1"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(content().json("{\"name\":\"rithin\",\"email_address\":\"rithn@tataelxsi.com\",\"phone_number\":\"7889794455\",\"status\":true,\"id\":1},{\"name\":\"lakshmi kishore\",\"email_address\":\"lakshmikishore@tataelxsi.com\",\"phone_number\":\"99900556211\",\"status\":true,\"id\":2}"));
//
//        verify(custService).;
//    }
    
  
//    
    @Test
    @WithMockUser()
    public void testgetAllCustomers() throws Exception {
    	
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{\"name\":\"rithin\",\"email_address\":\"rithn@tataelxsi.com\",\"phone_number\":\"7889794455\",\"status\":true,\"id\":1},{\"name\":\"lakshmi kishore\",\"email_address\":\"lakshmikishore@tataelxsi.com\",\"phone_number\":\"99900556211\",\"status\":true,\"id\":2},{\"name\":\"test1\",\"email_address\":\"test@tataelxsi.com\",\"phone_number\":\"8897556211\",\"status\":true,\"id\":3}]"))
                //.andExpect(jsonPath("$.*", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists());
//                .andExpect(MockMvcResultMatchers.)
//                .andExpect(jsonPath("$.title", Matchers.is("Greetings")))
//                .andExpect(jsonPath("$.value", Matchers.is("Hello World")))
//                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }
    
    
    @Test
    @WithMockUser()
    public void testgetCustomers() throws Exception {
    	
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"name\":\"rithin\",\"email_address\":\"rithn@tataelxsi.com\",\"phone_number\":\"7889794455\",\"status\":true,\"id\":1}"))
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists());
//                .andExpect(MockMvcResultMatchers.)
//                .andExpect(jsonPath("$.title", Matchers.is("Greetings")))
//                .andExpect(jsonPath("$.value", Matchers.is("Hello World")))
//                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }
    
    
//    @Test
//    @WithMockUser()
//    public void putCustomer() throws Exception {
//
//    	CustomerDomain custMockreturn = new CustomerDomain();
//		//CustomerInfoRepo
//    	CustomerDomain custMock = new CustomerDomain();
//    	custMock.setId(2);
//    	custMock.setName("Ravi");
//    	custMock.setEmail_address("Ravi@tataelxsi.com");
//    	custMock.setPhone_number("9447919911");
//    	custMock.setStatus(true);
//    	
//      when(custService.insertCustomerService(custMock)).thenReturn(custMockreturn);
//
//      mockMvc.perform(MockMvcRequestBuilders.put("/customer/custMock")
//    		  .accept(MediaType.APPLICATION_JSON))
//               .andExpect(MockMvcResultMatchers.content().json("{\"name\":\"Ravi\",\"email_address\":\"Ravi@tataelxsi.com\",\"phone_number\":\"9447919911\",\"status\":true,\"id\":2}") )
//               .andExpect((MockMvcResultMatchers.status().isOk()));
//              // .andExpect(jsonPath("$.*", Matchers.hasSize(3))));
//      //assertEquals(custMockreture.getName(),custMockreturn.getName());
//    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//    @Test
//    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
//    public void mytest1() throws Exception {
//        mockMvc.perform(get("/someApi"))
//            .andExpect(status().isOk());
//    }
//
//    @Test
//    public void getAllCustomers() throws Exception {
//
//    	//restTemplate.withBasicAuth("technical","Assessment");
//    	
//    //	String authStr = "technical:Assessment";
//    	String url = "http://localhost:" + port + "/customer/";
//     //   String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());
//
//        // create headers
//       
//       // HttpEntity<Employee> request = new HttpEntity<>(employee, headers);
//        
//        
////        ResponseEntity<String> result = template.withBasicAuth("spring", "secret")
////  	          .getForEntity("/private/hello", String.class);
//     
////        ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.GET, request, String.class);
//        
//       // System.out.println(response.getBody());
//        ResponseEntity<String> response = restTemplate.withBasicAuth("technical", "Assessment").getForEntity(url, String.class);
//			//new URL("http://localhost:" + port + "/customer/").toString(), String.class);
//        assertEquals(response.getStatusCode(),HttpStatus.OK);
//        response
//       assertEquals('[{"name":"rithin","email_address":"rithn@tataelxsi.com","phone_number":"7889794455","status":true,"id":1},{"name":"lakshmi kishore","email_address":"lakshmikishore@tataelxsi.com","phone_number":"99900556211","status":true,"id":2},{"name":"test1","email_address":"test@tataelxsi.com","phone_number":"8897556211","status":true,"id":3}]')
//
//
//        assertEquals("[\r\n" + 
//        		"    {\r\n" + 
//        		"        \"name\": \"rithin\",\r\n" + 
//        		"        \"email_address\": \"rithn@tataelxsi.com\",\r\n" + 
//        		"        \"phone_number\": \"7889794455\",\r\n" + 
//        		"        \"status\": true,\r\n" + 
//        		"        \"id\": 1\r\n" + 
//        		"    },\r\n" + 
//        		"    {\r\n" + 
//        		"        \"name\": \"lakshmi kishore\",\r\n" + 
//        		"        \"email_address\": \"lakshmikishore@tataelxsi.com\",\r\n" + 
//        		"        \"phone_number\": \"99900556211\",\r\n" + 
//        		"        \"status\": true,\r\n" + 
//        		"        \"id\": 2\r\n" + 
//        		"    },\r\n" + 
//        		"    {\r\n" + 
//        		"        \"name\": \"test1\",\r\n" + 
//        		"        \"email_address\": \"test@tataelxsi.com\",\r\n" + 
//        		"        \"phone_number\": \"8897556211\",\r\n" + 
//        		"        \"status\": true,\r\n" + 
//        		"        \"id\": 3\r\n" + 
//        		"    }\r\n" + 
//        		"]", response.getBody());
//
//    }

}
