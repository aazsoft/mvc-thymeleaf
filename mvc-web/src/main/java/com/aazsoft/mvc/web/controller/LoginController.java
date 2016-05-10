package com.aazsoft.mvc.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aazsoft.mvc.web.form.LoginForm;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute(value = "loginForm") LoginForm loginForm) {
		System.out
				.println(loginForm.getEmail() + "-" + loginForm.getPassword());
		return "admin";
	}

}
