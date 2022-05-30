package com.sts.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/jhu")
public class App {
	@RequestMapping("/home")
	public String home() {
		System.out.println("hiii");
		return "index";
	}
	
	@RequestMapping(path="/jdk", method=RequestMethod.POST)
	public String sam(Model model) {
		ArrayList< String> Employee=new ArrayList<String>();
		Employee.add("Pratham");
		Employee.add("Sham");
		Employee.add("Sundar");
		Employee.add("Rahul");
		Employee.add("Pratham");
		model.addAttribute("name",Employee);
		return "sam";
	}
	
	@RequestMapping("/sam")
    public String get() {
		return "pratham";
    	
    }
	@RequestMapping ("/view")
	public ModelAndView ram() {
		ModelAndView model=new ModelAndView();
		
	    LocalDateTime now=LocalDateTime.now();
	    System.out.println(now);
		 model.addObject("time", now);
		 model.setViewName("yash");
		 
		 return model;
		
		

	   
	}
}
