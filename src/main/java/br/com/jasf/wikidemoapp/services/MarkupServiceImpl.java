package br.com.jasf.wikidemoapp.services;

import java.util.regex.*;

import org.springframework.stereotype.Service;

@Service
public class MarkupServiceImpl implements MarkupService {

	@Override
	public String convertToHTML(String input) {		
		String output = input;
		
		output = output.replaceAll("&", "&amp;");
		output = output.replaceAll("<", "&lt;");
		output = output.replaceAll(">", "&gt;");
		
		output = output.replaceAll("\\[code\\]", "<pre>");
		output = output.replaceAll("\\[/code\\]", "</pre>");
		
		// output = output.replaceAll("\\[\\[([^]]+)\\]\\]", "<a href=\"/wiki/$1\">$1</a>");
		output = output.replaceAll("\\[\\[([^]]+)\\]\\]", "<a href=\"/wiki/$1\">$1</a>");
		output = output.replaceAll("(ABC)", "[abc]$1[/abc]");
		
		return output;
	}
	
}
