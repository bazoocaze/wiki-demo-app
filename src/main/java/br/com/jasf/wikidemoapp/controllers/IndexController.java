package br.com.jasf.wikidemoapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping(path = { "", "/", "index.html" })
	public String index() {
		return ViewNames.RedirectToWelcome;
	}
	
}
