package com.jrp.pma.api.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app-api/security")
public class SecurityApiController {
	// Only used for testing. Get the CSRF token and set it as a part of the
	// POST request header (X-CSRF-TOKEN).
	@GetMapping("/csrf-token")
	public @ResponseBody String getCsrfToken(HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
		return token.getToken();
	}
}
