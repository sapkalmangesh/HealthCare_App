package com.ms.in.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ms.in.entity.Appointment;
import com.ms.in.exception.AppointmentNotFoundException;
import com.ms.in.service.IAppointmentService;
import com.ms.in.service.IDoctorService;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private IAppointmentService service;
	
	@Autowired
	private IDoctorService doctorService;
	
	private void showDoctorDynamically(Model model) {
		Map<Long,String> map = doctorService.getDoctorsName();
		model.addAttribute("doctors", map);
	}
	
	/**
	 * 
	 * @param model
	 * @param message
	 * @return
	 */
	@GetMapping("/register")
	public String showRegister( 
						Model model,
						@RequestParam (name = "message", required = false) String message
						) {
		model.addAttribute("message", message);
		showDoctorDynamically(model);
		return "AppointmentRegister";
		
	}
	
	/**
	 * 
	 * @param appointment
	 * @param attribute
	 * @return
	 */
	
	@PostMapping("/save")
	public String createAppointment(
							@ModelAttribute Appointment appointment,
							RedirectAttributes attribute
							) 
	{
		Long id = service.saveAppointment(appointment);
		String message = "Appointment "+id+" is created.";
		attribute.addAttribute("message", message);
		return "redirect:register";
	}
	
	/**
	 * 
	 * @param model
	 * @param message
	 * @return
	 */
	
	@GetMapping("/all")
	public String showAllAppointment(
							Model model,
							@RequestParam(name = "message",required = false) String message)
	{	
		List<Appointment> list = service.getAllAppointment();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "AppointmentData";
		
	}
	
	/**
	 * 
	 * @param id
	 * @param attributes
	 * @return
	 */
	
	@GetMapping("/delete")
	public String deleteAppointment(
							@RequestParam long id,
							RedirectAttributes attributes) {
		try {
			
			service.removeAppointment(id);
			attributes.addAttribute("message", "Appointment "+id+" removed successfully.");
			
		} catch (AppointmentNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
		}
		return "redirect:all";
	}
	
	/**
	 * 
	 * @param id
	 * @param attribute
	 * @param model
	 * @return
	 */
	@GetMapping("/edit")
	public String editAppointment(
							@RequestParam long id,
							RequestAttribute attribute,
							Model model) {
		String page = null;
		try {
			Appointment appointment = service.getOneAppointment(id);
			model.addAttribute("appointment", appointment);
			page = "AppointmentEdit";
		} catch (AppointmentNotFoundException e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
			page= "redirect:all";
		}
		return null;
	}
	
	public String update(
					@ModelAttribute Appointment appointment,
					RedirectAttributes attribute) {
		service.updateAppointment(appointment);
		attribute.addAttribute("message", "Appointment "+appointment.getId()+" updated successfully.");
		return "redirect:all";
		
	}
	
	
}
