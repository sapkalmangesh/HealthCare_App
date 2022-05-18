package com.ms.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		String message= "Specialization record "+id+" is created.";	
		model.addAttribute("message", message);
		return "SpecializationRegister";
	}
	
	/***	
	 * 3. get all Specialization data
	 * @param model
	 * @return
	 */
	@GetMapping("/all")
	public String viewAll(
			Model model,
			@RequestParam(value="message",required=false) String message
			) 
	{
		List<Specialization> list = service.getAllSpecialization();
		model.addAttribute("spec_list", list);
		model.addAttribute("message", message);
		return "SpecializationData";
		
	}
	
	/***
	 * 4. delete data by id
	 * delete data by id and send one message to ui via viewAll() methode using 
	 * concept 'redirect:all'(redirect to all).
	 * for this use 'RedirectAttribute' to send message to via viewAll() method
	 * @param id
	 * @param attributes
	 * @return
	 */
	@GetMapping("/delete")
	public String deleteData(
			@RequestParam Long id,
			RedirectAttributes attributes
			) 
	{
		service.removeSpecialization(id);
		attributes.addAttribute("message", "Specialization record "+id+" is removed.");
		return "redirect:all";
		
	}

}
