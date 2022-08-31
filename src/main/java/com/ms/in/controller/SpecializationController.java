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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ms.in.entity.Specialization;
import com.ms.in.exception.SpecializationNotFoundException;
import com.ms.in.service.ISpecializationService;
import com.ms.in.view.SpecializationExcelView;

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
		try {
			service.removeSpecialization(id);
			attributes.addAttribute("message", "Specialization record "+id+" is removed.");
			
		} catch (SpecializationNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
		}
		return "redirect:all";
		
	}
	
	/***
	 * 5. fetch data into edit page
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/edit")
	public String showEditData(
			@RequestParam Long id,
			Model model,
			RedirectAttributes attribute)
	{
		String page=null;
		try {
			Specialization spec = service.getOneSpecialization(id);
			model.addAttribute("specialization", spec);
			page= "SpecializationEdit";
		} catch (SpecializationNotFoundException e) {
			e.printStackTrace();
			attribute.addAttribute("message", e.getMessage());
			page = "redirect:all";
			}
		return page;
	}
	
	/***
	 * 6. update form data and redirect to all
	 * @param specialization
	 * @param attributes
	 * @return
	 */
	@PostMapping("/update")
	public String updateData(
			@ModelAttribute Specialization specialization,
			RedirectAttributes attributes) 
	{
		service.updateSpecialization(specialization);
		attributes.addAttribute("message", "Specialization record "+specialization.getSpecId()+" is Updated.");
		return "redirect:all";
	}
	
	@GetMapping("/checkCode")
	@ResponseBody
	public String validateSpecCode(
			@RequestParam String code, 
			@RequestParam Long id) 
	{
		String message = "";
		if(id==0 && service.isSpecCodeExist(code)) {//register check
			message = code + ", already exist.";
		}else if(id!=0 && service.isSpecCodeExistForEdit(code, id)) {//edit check
			message = code + ", already exist.";
		}
			return message;
	}
	
	/***
	 * On click 'ExcelExport' hyperlink in DataPage, fetch all record form
	 * database, convert into List<T> collection and send into 'ModelAndView'
	 * class object in the form of ModelMap memory.
	 * @return
	 */
	@GetMapping("/excel")
	public ModelAndView exportToExel() {
		ModelAndView m = new ModelAndView();
		m.setView(new SpecializationExcelView());
		//read data form db
		List<Specialization> list = service.getAllSpecialization();
		//send to exel impl class
		m.addObject("list",list);
		return m;
		
	}

}
