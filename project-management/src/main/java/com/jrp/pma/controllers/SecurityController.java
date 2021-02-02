package com.jrp.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jrp.pma.entities.UserAccount;
import com.jrp.pma.services.UserAccountService;

@Controller
public class SecurityController {

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	UserAccountService accountService;

	@GetMapping("/register")
	public String register(Model model) {
		UserAccount account = new UserAccount();
		model.addAttribute("account", account);
		return "security/register";
	}

	@PostMapping("/register/save")
	public String saveUser(@ModelAttribute(value = "account") UserAccount account) {
		account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
		accountService.save(account);
		return "redirect:/";
	}
}
