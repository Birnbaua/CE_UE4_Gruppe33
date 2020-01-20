package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import order.Order;

@Controller
public class ConfigurationController {
	private Order order;
	
	@RequestMapping(value = "/handlebars", method = RequestMethod.GET)
	public String handlebars(Model model) {
		model.addAttribute("types", getHandlebars());
		return "lenkertyp";
	}
	
	@RequestMapping(value = "/materials", method = RequestMethod.GET)
	public String materials(@RequestParam("handlebar") String handlebar, Model model) {
		model.addAttribute("types", getMaterials(handlebar));
		order = new Order();
		order.setHandlebar(handlebar);
		return "material";
	}
	
	@RequestMapping(value = "/gears", method = RequestMethod.GET)
	public String gears(@RequestParam("material") String material, Model model) {
		model.addAttribute("types", getGearlevers(order.getHandlebar()));
		System.out.println("Material:" + material);
		order.setMaterial(material);
		return "schaltung";
	}
	
	@RequestMapping(value = "/handles", method = RequestMethod.GET)
	public String handles(@RequestParam("gear") String gear, Model model) {
		model.addAttribute("types", getHandles(order.getMaterial()));
		order.setGearLever(gear);
		return "griff";
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String order(@RequestParam("handle") String handle, Model model) {
		order.setHandle(handle);
		model.addAttribute("order", postTO("https://www.maripavi.at/bestellung",order));
		return "bestellung";
	}
	
	private String getFrom(String uri) {
		RestTemplate rt = new RestTemplate();
		String result = rt.getForObject(uri, String.class);
		return result;
	}
	
	private String postTO(String uri, Order order) {
		RestTemplate rt = new RestTemplate();
		String result = rt.postForObject(uri + String.format("?griff=%s&lenkertyp=%s&material=%s&schaltung=%s", 
				order.getHandle(),order.getHandlebar(), order.getMaterial(), order.getGearLever()),null,String.class);
		return result;
	}

	private String[] getArrayFrom(String uri) {
		return getFrom(uri).replace("[", "").replace("]", "").replaceAll("\"", "").split(",");
	}
	
	private String getHandlebars() {
		return getFrom("https://www.maripavi.at/produkt/lenkertyp");
	}
	
	private String getMaterials(String handlebar) {
		return getFrom("https://www.maripavi.at/produkt/material" + "?lenkertyp=" + handlebar);
	}
	
	private String getHandles(String material) {
		return getFrom("https://www.maripavi.at/produkt/griff" + "?material=" + material);
	}
	
	private String getGearlevers(String handlebar) {
		return getFrom("https://www.maripavi.at/produkt/schaltung" + "?lenkertyp=" + handlebar);
	}
}