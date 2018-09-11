package br.com.jasf.wikidemoapp.data;

import java.util.Set;

import br.com.jasf.wikidemoapp.model.WikiPage;

public interface WikiService {

	WikiPage findByTitle(String name);
	
	WikiPage save(WikiPage wiki);
	
	boolean delete(WikiPage wiki);
	
	Set<WikiPage> search(String text);
	
}
