package com.aazsoft.mvc.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.aazsoft.mvc.security.model.CurrentUser;

@ControllerAdvice
public class UserControllerAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(UserControllerAdvice.class);

    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUser(Authentication authentication) {
        CurrentUser currentUser = (authentication == null) ? null : (CurrentUser) authentication.getPrincipal();
        LOG.debug("getting current user, user={}", currentUser);
        return currentUser;
    }


}
