package br.com.jasf.wikidemoapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import br.com.jasf.wikidemoapp.data.WikiService;
import br.com.jasf.wikidemoapp.model.WikiArticle;

@Controller
public class EditWikiController {

	WikiService wikiService;

	public EditWikiController(WikiService wikiService) {
		this.wikiService = wikiService;
	}

	@GetMapping(path = "/edit/{name}")
	public String getWiki(@PathVariable(name = "name") String name, Model model) {
		model.addAttribute("name", name);
		model.addAttribute("pageTitle", "Edit " + name + " - Wiki");

		WikiArticle wikiArticle = wikiService.findByName(name);
		if (wikiArticle == null) {
			model.addAttribute("title", name);
			model.addAttribute("contents", "(novo conteúdo)");
		} else {
			model.addAttribute("title", wikiArticle.getTitle());
			model.addAttribute("contents", wikiArticle.getContents());
		}

		return ViewNames.EditWiki;
	}

}
