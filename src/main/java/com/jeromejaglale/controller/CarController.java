package com.jeromejaglale.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeromejaglale.domain.Car;
import com.jeromejaglale.service.CarService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;



@Controller
public class CarController {
	@Autowired
	private CarService carService;

	@Value("${configkey1}")
	private String keyname;

	@Autowired
	private Environment environment;
	
	@RequestMapping("/car/list")
	public void carList(Model model) {
		List<Car> carList = carService.findAll();
		model.addAttribute("carList", carList);
		System.out.println("key value is using value annotation " + keyname);
		System.out.println("key value is using environment package  " + environment.getProperty("configkey1"));
		System.out.println("key value is using system package " + System.getenv().getOrDefault("configkey1", "Hi"));
		
		
	}
	
	@RequestMapping("/car/add")
	public void carAdd() {
	}
	
	@RequestMapping(value="/car/add", method=RequestMethod.POST)
	public String carAddSubmit(@ModelAttribute("car") @Valid Car car, BindingResult result) {
		if(result.hasErrors()) {
			// show the form again, with the errors
			return "car/add";
		}
	
		// validation was successful
		carService.add(car);
		return "redirect:/car/list";
			
	}
}