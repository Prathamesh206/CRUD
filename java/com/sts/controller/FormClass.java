package com.sts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sts.model.User;

@Controller
public class FormClass {

	@RequestMapping("/form")	
	public String showForm() {
		return "form";
	} 
//	@ RequestMapping(path="/submit",method=RequestMethod.POST)
//	public ModelAndView getData(@RequestParam("email") String email,@RequestParam("name") String name,@RequestParam("password") String password) {
//		ModelAndView model=new ModelAndView();
//		User user=new User();
//////		model.addObject("email", email);
////		System.out.println(email);
////		model.addObject("name", name);
////		System.out.println(email);                        using requestparam
////
////		model.addObject("password", password);
//		user.setEmail(email);
//		user.setName(name);
//		user.setPassword(password);
//		model.addObject("name", user);
//		model.setViewName("display");
//
//
//		return model;

//
//	}
	
	@ RequestMapping(path="/submit",method=RequestMethod.POST)
	public ModelAndView getData(@ModelAttribute User user) {
		ModelAndView model=new ModelAndView();
//		User user=new User();
////		model.addObject("email", email);
//		System.out.println(email);
//		model.addObject("name", name);
//		System.out.println(email);                        using requestparam
//
////		model.addObject("password", password);
//		user.setEmail(email);
//		user.setName(name);
//		user.setPassword(password);
		model.addObject("name", user);
		model.setViewName("display");


		return model;

	}
	

}
