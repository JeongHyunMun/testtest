package com.example.demo.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	@RequestMapping({"/", "/{path:[^\\.]*}"})
	public String main(ModelAndView mav) {
		return "forward:/index.html";
	}

}
