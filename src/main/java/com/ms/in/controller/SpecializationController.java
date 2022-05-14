package com.ms.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ms.in.entity.Specialization;
import com.ms.in.service.ISpecializationService;

@Controller
@RequestMapping("/spec")
public class SpecializationController {
	
	@Autowired
	private ISpecializationService service;
	
	/***
	 * 1. get all Specialization data
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
