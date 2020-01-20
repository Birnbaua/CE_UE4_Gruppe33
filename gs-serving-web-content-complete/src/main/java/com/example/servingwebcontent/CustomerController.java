package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import customer.Customer;

@Controller
public class CustomerController {
	
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public String login(@ModelAttribute(name="Customer") Customer customer, Model model) {
		//model.addAttribute("name", name);
		System.out.println("Customer Post");
		System.out.println(customer.getFirst_name());
		System.out.println(customer.getLast_name());
		
		RestTemplate rt = new RestTemplate();
		String result = rt.postForObject("http://localhost:8080/saveCustomer?first_name=" + customer.getFirst_name() + "&last_name=" + customer.getLast_name(),null,String.class);
		
		model.addAttribute("name", result);
		return "showCustomer";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login1(Model model) {
		//model.addAttribute("name", name);
		return "createCustomer";
	}
	
}