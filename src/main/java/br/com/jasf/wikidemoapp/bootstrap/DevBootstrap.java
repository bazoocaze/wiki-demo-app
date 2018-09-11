package br.com.jasf.wikidemoapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.jasf.wikidemoapp.data.WikiService;
import br.com.jasf.wikidemoapp.model.WikiArticle;

@Component
public class DevBootstrap implements CommandLineRunner {

	private WikiService wikiService;
	
	public DevBootstrap(WikiService wikiService) {
		this.wikiService = wikiService;
	}

	@Override
	public void run(String... args) throws Exception {
		WikiArticle wiki = new WikiArticle();
		
		wiki.setName("index");
		wiki.setTitle("index");
		wiki.setContents("Conteúdo de index");
		wikiService.save(wiki);
		
		wiki.setName("teste");
		wiki.setTitle("teste");
		wiki.setContents("Conteúdo de teste");
		wikiService.save(wiki);
	}
	
}
