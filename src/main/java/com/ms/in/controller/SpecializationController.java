package com.ms.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ms.in.entity.Specialization;
import com.ms.in.service.ISpecializationService;

@Controller
@RequestMapping("/spec")
public class SpecializationController {
	
	@Autowired
	private ISpecializationService service;
	
	/***
	 * 1.Register specialization
	 */
	@GetMapping("/register")
	public String displaySpecialization() {
		return "SpecializationRegister";
	}
	/***
	 * 2. On form submit save form data into database
	 * @param specialization
	 * @param model
	 * @return
	 */
	@PostMapping("/save")
	public String saveSpecialization(
			@ModelAttribute Specialization specialization,
			Model model) 
	{
		Long id = service.saveSpecialization(specialization);
		String message= "Specialization with "+id+" is created.";	
		model.addAttribute("message", message);
		return "SpecializationRegister";
	}
	
	/***	
	 * 3. get all Specialization data
	 * @param model
	 * @return
	 */
	@GetMapping("/all")
	public String viewAll(Model model) {
		List<Specialization> list = service.getAllSpecialization();
		model.addAttribute("spec_list", list);
		return "SpecializationData";
		
	}

}
