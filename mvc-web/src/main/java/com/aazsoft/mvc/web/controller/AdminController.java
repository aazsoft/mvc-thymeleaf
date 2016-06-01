package com.aazsoft.mvc.web.controller;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aazsoft.mvc.service.UserService;
import com.aazsoft.mvc.service.form.UserCreateForm;
import com.aazsoft.mvc.service.form.UserSearchForm;

@Controller
public class AdminController {

	private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/user/create", method = RequestMethod.GET)
	public ModelAndView getUserCreatePage() {
		LOG.debug("Getting user create form");
		return new ModelAndView("userCreation", "userCreateForm", new UserCreateForm());
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/user/create", method = RequestMethod.POST)
	public String handleUserCreateForm(
			@Valid @ModelAttribute("userCreateForm") UserCreateForm form,
			BindingResult bindingResult) {
		LOG.debug("Processing user create form={}, bindingResult={}", form, bindingResult);
		if (bindingResult.hasErrors()) {
			return "userCreation";
		}
		try {
			form.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
			userService.create(form);
		} catch (DataIntegrityViolationException e) {
			// probably email already exists - very rare case when multiple
			// admins are adding same user
			// at the same time and form validation has passed for more than one
			// of them.
			LOG.warn(
					"Exception occurred when trying to save the user, assuming duplicate email",
					e);
			bindingResult.reject("email.exists", "Email already exists");
			return "userCreation";
		}
		// ok, redirect
		return "redirect:/users";
	}
	
	@RequestMapping("/users")
    public ModelAndView getUsersPage() {
        LOG.debug("Getting users page");
        return new ModelAndView("users", "users", userService.getAllUsers());
    }
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/user/search", method = RequestMethod.GET)
	public ModelAndView initializeSearchPage(Map<String, Object> model) {
		LOG.debug("Getting user search form");
		model.put("userSearchForm", new UserSearchForm());
		return new ModelAndView("searchUsers", model);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/user/search", method = RequestMethod.POST)
	public ModelAndView handleSearchPage(
			@Valid @ModelAttribute("userSearchForm") UserSearchForm form,
			BindingResult bindingResult, Map<String, Object> model) {
		LOG.debug("Processing user search form={}, bindingResult={}", form, bindingResult);
		model.put("users", userService.getAllUsers());
		return new ModelAndView("searchUsers", model);
	}
}
