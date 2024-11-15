package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;


@Controller
public class CourseController {
	
	@Autowired
	CourseRepository crep;
	
	@GetMapping("/")
	public String addCourse()
	{
		return "add";
	}
	
	
	@PostMapping("/course/add")
	public String addCourse(Course c)
	{
		
		crep.save(c);
		return "redirect:/course/display";
	}
	
	@GetMapping("/course/display")
	public String displayCourse(Model model)
	{
		List<Course> c_list=(List<Course>)crep.findAll();
		model.addAttribute("course",c_list);
		return "display";
	}
	
	@GetMapping("/course/delete/{id}")
	public String deleteCourse(@PathVariable Integer id)
	{
		crep.deleteById(id);
		return "redirect/course/display";
	}
	
	@GetMapping("course/edit/{id}")
	public String editCourse(@PathVariable Integer id,Model model)
	{
		Course cs = crep.findById(id).get();
		model.addAttribute("course",cs);
		return "edit";
	}
	
	
	@PostMapping("/course/edit")
	public String editCourse(Course c)
	{
		Integer id = c.getId();
		String name = c.getName();
		String description = c.getDescription();
		
		Course cDB = crep.findById(id).get();
		
		cDB.setName(name);
		cDB.setDescription(description);
		
		crep.save(cDB);
		
		return "redirect:/course/display";
	}
}
