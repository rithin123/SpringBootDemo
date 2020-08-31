package com.crud.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="tbl_customer")
public class CustomerDomain {
	
	public CustomerDomain()
	{
		
	}
	
	    public CustomerDomain(CustomerDomain cust) {
		super();
		this.number = cust.getId();
		this.name = cust.getName();
		this.email_address =cust.getEmail_address();
		this.phone_number = cust.getPhone_number();
		this.status = cust.isStatus();
	}

		@Id
	    @Column(name = "number", nullable = false)
	    @GeneratedValue
	    private Integer number;
	    
	    @Column(name = "name")
	    private String name;
	    
	    
	    @Column(name = "email")
	    private String email_address;
	    
	    
	    @Column(name = "phone")
	    private String phone_number;
	    
	    @Column(name="status")
	    private boolean status;

		


		public Integer getId() {
			return number;
		}

		public void setId(Integer number) {
			this.number = number;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail_address() {
			return email_address;
		}

		public void setEmail_address(String email_address) {
			this.email_address = email_address;
		}

		public String getPhone_number() {
			return phone_number;
		}

		public void setPhone_number(String phone_number) {
			this.phone_number = phone_number;
		}

		public boolean isStatus() {
			return status;
		}

		public void setStatus(boolean status) {
			this.status = status;
		}

		@Override
		public String toString() {
			return "CustomerDomain [number=" + number + ", name=" + name + ", email_address=" + email_address
					+ ", phone_number=" + phone_number + ", status=" + status + "]";
		}
	    
	
	
}
