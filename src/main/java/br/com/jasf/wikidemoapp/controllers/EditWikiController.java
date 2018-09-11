package br.com.jasf.wikidemoapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import br.com.jasf.wikidemoapp.data.WikiService;
import br.com.jasf.wikidemoapp.model.WikiPage;

@Controller
public class EditWikiController {

	WikiService wikiService;

	public EditWikiController(WikiService wikiService) {
		this.wikiService = wikiService;
	}

	@GetMapping(path = "/edit/{title}")
	public String getWiki(@PathVariable(name = "title") String title, Model model) {
		model.addAttribute("title", title);
		model.addAttribute("pageTitle", "Edit " + title + " - Wiki");

		WikiPage wikiPage = wikiService.findByTitle(title);
		if (wikiPage == null) {
			model.addAttribute("contents", "(novo conteúdo)");
		} else {
			model.addAttribute("contents", wikiPage.getContents());
		}

		return ViewNames.EditWiki;
	}

	@PostMapping(path = "/edit/{title}")
	public String saveWiki(@PathVariable(name = "title") String title, @RequestParam(name = "contents") String contents,
			Model model) {
		WikiPage wikiPage;

		wikiPage = wikiService.findByTitle(title);
		if (wikiPage == null) {
			wikiPage = new WikiPage();
			wikiPage.setTitle(title);
		}

		wikiPage.setContents(contents);
		wikiService.save(wikiPage);

		return ViewNames.RedirectToWiki(title);
	}

}
