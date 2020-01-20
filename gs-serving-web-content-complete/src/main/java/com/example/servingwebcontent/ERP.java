package com.example.servingwebcontent;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import customer.Customer;

@RestController
public class ERP {
	
	
	@PostMapping("/saveCustomer")
	ResponseEntity<Customer> newCustomer(@RequestParam String first_name, @RequestParam String last_name){
		
		/*
		 * save in db or something else
		 */
		
		return ResponseEntity.status(201).header("Customer", "created new Customer").body(new Customer(first_name,last_name));
	}
	
}
