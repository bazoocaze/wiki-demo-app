package br.com.jasf.wikidemoapp.data;

import br.com.jasf.wikidemoapp.model.WikiArticle;

public interface WikiService {

	WikiArticle findByName(String name);
	
	WikiArticle save(WikiArticle item);
	
	boolean delete(WikiArticle item);
	
}
