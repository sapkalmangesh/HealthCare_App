package com.ms.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ms.in.entity.Doctor;
import com.ms.in.exception.DoctorNotFoundException;
import com.ms.in.service.IDoctorService;
import com.ms.in.service.ISpecializationService;
import com.ms.in.util.MyMailUtil;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private IDoctorService service;
	
	@Autowired
	private ISpecializationService specializationservice;
	
	@Autowired
	private MyMailUtil mailUtil;
	
	private void createDynamicUi(Model model) {
		model.addAttribute("specializations", specializationservice.getSpecIdAndName());
	}
	
	//1.show register
	@GetMapping("/register")
	public String showRegister(
			@RequestParam(value = "message", required = false) String message,
			Model model
			) 
	{
		model.addAttribute("message", message);
		createDynamicUi(model);
		return "DoctorRegister";
	}
	
	//2.save Doctor
	@PostMapping("/save")
	public String createDoctor(
			@ModelAttribute Doctor doctor,
			RedirectAttributes attributes
			)
	{
		Long id = service.saveDoctor(doctor);
		String message = "Doctor '"+id+" ' is created";
		attributes.addAttribute("message", message);
		if(id!=null) {
			new Thread(new Runnable() {
				public void run() {
					
					mailUtil.send(
							doctor.getEmail(), 
							"SUCCESS", 
							message, 
							new ClassPathResource("/static/myres/sample.pdf"));
				}
				
			}).start();
		}
		return "redirect:register";
	}
	
	//3.showAllDoctor
	@GetMapping("/all")
	public String showData(
			Model model,
			@RequestParam(value = "message",required = false) String message
			)
	{
		List<Doctor> list = service.getAllDoctors();
		model.addAttribute("doc_list", list);
		model.addAttribute("message", message);						
		return "DoctorData";
	}
	
	//4.Delete Doctor
	@GetMapping("/delete")
	public String deleteDoctor(
			@RequestParam Long id,
			RedirectAttributes attribute
			) 
	{	
		try {
			service.removeDoctor(id);
			attribute.addAttribute("message","Doctor record '"+id+"' removed successfully.");
			
		} catch (DoctorNotFoundException e) {
			e.printStackTrace();
			attribute.addAttribute("message",e.getMessage());
		}
		return "redirect:all";
		
	}
	//5. Edit Doctor
	@GetMapping("/edit")
	 public String editDoctorData(
			 @RequestParam Long id,
			 Model model,
			 RedirectAttributes attribute) 
	 {
		 String page= null;
		 try {
			 Doctor doctor=service.getOneDoctor(id);
			 model.addAttribute("doctor", doctor);
			 page = "DoctorEdit";
			 createDynamicUi(model);
			
		} catch (DoctorNotFoundException e) {
			e.printStackTrace();
			attribute.addAttribute("message", e.getMessage());
			page = "redirect:all";
		}
		 return page;
		 
	 }
	
	//6.update Doctor
	@PostMapping("/update")
	 public String doUpdate(
			 @ModelAttribute Doctor doctor,
			 RedirectAttributes attribute
			 )
	 {
		 service.updateDoctor(doctor);
		 attribute.addAttribute("message","Doctor id '"+doctor.getId()+"' is updated successfully.");
		 return "redirect:all";
		 
	 }

}
