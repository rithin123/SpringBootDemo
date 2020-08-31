package com.crud.demo.repo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crud.demo.model.CustomerDomain;

@Transactional
public interface CustomerInfoRepo extends JpaRepository<CustomerDomain, Integer> {
	
	List<CustomerDomain> findAllByStatus(boolean procesed);
	
//	int updateCustomer(Integer number, String name, String email, String phone,boolean status);
	
//	@Transactional
//	@Modifying
//	@Modifying(clearAutomatically = true)
//	@Query(value="insert into tbl_customer (number,name,email,phone,status) VALUES (:number,:name,:email,:phone,:status)", nativeQuery = true)
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE tbl_customer  SET name = ?2, email = ?3, phone = ?4, status = ?5 where number = ?1",  nativeQuery = true)
	int updateCustomer(Integer number, String name, String email, String phone,boolean status);
//	@Transactional
//	@Modifying
//	@Query("update tbl_customer set name = :name, email = :email, phone = :phone, status = :status where number = :number")
//	int updateCustomer(@Param("number")  Integer number, @Param("name")  String name,@Param("email")  String email, @Param("phone")  String phone,@Param("status")  boolean status);
	
	@Query(value="SELECT * FROM tbl_customer cust WHERE cust.number=?1", nativeQuery= true)
	CustomerDomain findByNumber(int number);
	
//	@Modifying(clearAutomatically = true)
//	@Query(value="delete from tbl_customer cust where cust.number=?1" , nativeQuery= true)
	Integer deleteByNumber(int number);
	
	
   

}
