package com.jrp.pma.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppErrorController implements ErrorController {

	@GetMapping("/error")
	public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if (status != null) {
			int statusCode = Integer.valueOf(status.toString());
			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				return "errorpages/error-404";
			}
			if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				return "errorpages/error-500";
			}
			if (statusCode == HttpStatus.FORBIDDEN.value()) {
				return "errorpages/error-403";
			}
		}
		return "errorpages/error";
	}

	@Override
	public String getErrorPath() {
		// Return a custom path to call when an error occurred.
		return "/error";
	}

}
