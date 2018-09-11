package br.com.jasf.wikidemoapp.controllers;

public class ViewNames {
	public static final String EditWiki = "edit_wiki";
	public static final String WikiNotFound = "wiki_not_found";
	public static final String Wiki = "wiki";
	public static final String SearchResults = "search_results";

	public static final String RedirectToWelcome = "redirect:/wiki/Welcome";

	public static String RedirectToWiki(String title) {
		return "redirect:/wiki/" + title;
	}
}
