package com.ms.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ms.in.entity.Patient;
import com.ms.in.exception.PatientNotFoundException;
import com.ms.in.service.IPatientService;

@Controller
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	private IPatientService service;
	
	//1. Show patient register page.
	
	@GetMapping("/register")
	public String showRegister(
					@RequestParam(name="message", required = false) String message,
					Model model)
	{
		model.addAttribute("message", message);
		return "PatientRegister";	
	}
	
	//2. Save patient register form data.
	
	@PostMapping("/save")
	public String savePatient(
					@ModelAttribute Patient patient,
					RedirectAttributes attributes
					) 
	{
		Long id = service.savePatient(patient);
		String message = "Patient "+ id +" is created.";
		attributes.addAttribute("message", message);
		return "redirect:register";
	}
	
	//3. Show all patient data.
	
	@GetMapping("/all")
	public String showPatientData(
			Model model,
			@RequestParam(name = "message",required = false) String message) 
	{
		List<Patient> list = service.getAllPatients();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "PatientData";
		
	}
	
	//4. Delete patient using id.
	
	@GetMapping("/delete")
	public String deletePatient(
						@RequestParam Long id,
						RedirectAttributes attribute) 
	{
		try {
			service.removePatient(id);
			attribute.addAttribute("message", "Patient "+id+" remove successfully.");
		} catch (PatientNotFoundException e) {
			e.printStackTrace();
			attribute.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:all";
		
	}
	
	//5. Edit patient
	
	@GetMapping("/edit")
	public String editPatient(
					@RequestParam Long id,
					RedirectAttributes attribute,
					Model model) 
	{
		String page = null;
		try {
			Patient patient = service.getOnePatient(id);
			model.addAttribute("patient", patient);
			page = "PatientEdit";
			
		} catch (PatientNotFoundException e) {
			e.printStackTrace();
			attribute.addAttribute("message", e.getMessage());
			page= "redirect:all";
		}
		
		return page;
		
	}
	
	//6. Update patient
	
	public String updatePatient(
					@ModelAttribute Patient patient,
					RedirectAttributes attribute) 
	{
		service.updatePatient(patient);
		attribute.addAttribute("message", "Patient "+patient.getId()+ "updated successfully.");
		return "redirect:all";
		
	}
	
	

}
