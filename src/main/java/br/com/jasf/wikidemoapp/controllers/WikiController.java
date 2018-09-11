package br.com.jasf.wikidemoapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import br.com.jasf.wikidemoapp.data.WikiService;
import br.com.jasf.wikidemoapp.model.WikiPage;
import br.com.jasf.wikidemoapp.services.MarkupService;

@Controller
public class WikiController {

	WikiService wikiService;
	MarkupService markupService;

	public WikiController(WikiService wikiService, MarkupService markupService) {
		this.wikiService = wikiService;
		this.markupService = markupService;
	}

	@GetMapping(path = "/wiki")
	public String wikiIndex(Model model) {
		return ViewNames.RedirectToWelcome;
	}

	@GetMapping(path = "/wiki/{title}")
	public String getWiki(@PathVariable(name = "title") String title, Model model) {

		model.addAttribute("title", title);

		WikiPage wikiPage = wikiService.findByTitle(title);
		if (wikiPage == null) {
			return ViewNames.WikiNotFound;
		}

		model.addAttribute("pageTitle", wikiPage.getTitle() + " - Wiki Demo");
		model.addAttribute("contents", wikiPage.getContents());
		model.addAttribute("htmlContents", markupService.convertToHTML(wikiPage.getContents()));

		return ViewNames.Wiki;
	}

}
