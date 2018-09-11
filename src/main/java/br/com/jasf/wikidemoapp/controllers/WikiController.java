package br.com.jasf.wikidemoapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import br.com.jasf.wikidemoapp.data.WikiService;
import br.com.jasf.wikidemoapp.model.WikiArticle;

@Controller
public class WikiController {
	
	WikiService wikiService;
	
	public WikiController(WikiService wikiService) {
		this.wikiService = wikiService;
	}

	@GetMapping(path = "/wiki/{name}")
	public String getWiki(@PathVariable(name="name") String name, Model model)
	{
		model.addAttribute("name", name);
		WikiArticle wikiArticle = wikiService.findByName(name);
		if(wikiArticle == null) {
			return ViewNames.WikiNotFound;
		}
		
		model.addAttribute("pageTitle", wikiArticle.getTitle() + " - Wiki");
		model.addAttribute("title", wikiArticle.getTitle());
		model.addAttribute("contents", wikiArticle.getContents());
		
		return ViewNames.Wiki;
	}
	
}
