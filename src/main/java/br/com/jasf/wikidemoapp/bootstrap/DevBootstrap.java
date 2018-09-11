package br.com.jasf.wikidemoapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.jasf.wikidemoapp.data.WikiService;
import br.com.jasf.wikidemoapp.model.WikiPage;

@Component
public class DevBootstrap implements CommandLineRunner {

	private WikiService wikiService;
	
	public DevBootstrap(WikiService wikiService) {
		this.wikiService = wikiService;
	}

	@Override
	public void run(String... args) throws Exception {
		WikiPage wiki = new WikiPage();
		
		wiki.setTitle("Welcome");
		wiki.setContents("Conteúdo de Welcome");
		wikiService.save(wiki);
		
		wiki.setTitle("Teste");
		wiki.setContents("Conteúdo de Teste");
		wikiService.save(wiki);
	}
	
}
