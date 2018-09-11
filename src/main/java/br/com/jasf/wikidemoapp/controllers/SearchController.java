package br.com.jasf.wikidemoapp.controllers;

import java.util.Set;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import br.com.jasf.wikidemoapp.data.WikiService;
import br.com.jasf.wikidemoapp.model.WikiPage;

@Controller
public class SearchController {

	WikiService wikiService;

	public SearchController(WikiService wikiService) {
		this.wikiService = wikiService;
	}

	@GetMapping(path = { "/search", "/search/" })
	public String searchWiki(@RequestParam(name = "search") String search, Model model) {
		Set<WikiPage> serchResults = wikiService.search(search);

		model.addAttribute("search", search);
		model.addAttribute("searchResults", serchResults);

		return ViewNames.SearchResults;
	}

}
