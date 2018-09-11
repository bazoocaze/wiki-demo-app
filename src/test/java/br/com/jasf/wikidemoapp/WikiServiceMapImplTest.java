package br.com.jasf.wikidemoapp;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.jasf.wikidemoapp.data.WikiServiceMapImpl;
import br.com.jasf.wikidemoapp.model.WikiPage;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { WikiServiceMapImpl.class })
public class WikiServiceMapImplTest {

	@Autowired
	public WikiServiceMapImpl service;

	@Test
	public void testFindByTitle_ValidateInput() {

		// garante que recusa name=null
		assertThatIllegalArgumentException().isThrownBy(() -> {
			service.findByTitle(null);
		});

		// garante que recusa name=""
		assertThatIllegalArgumentException().isThrownBy(() -> {
			service.findByTitle("");
		});

	}

	@Test
	public void testFindByTitle_ValidateOperation() {

		// garante que retorna null caso não encontre o artigo
		Assert.assertNull(service.findByTitle("12345"));

		WikiPage wiki = new WikiPage();
		wiki.setId(1);
		wiki.setTitle("Teste");
		wiki.setContents("teste");

		// salva alguns artigos de teste
		service.save(wiki);

		wiki.setTitle("Novo");
		service.save(wiki);

		wiki.setTitle("Outro");
		service.save(wiki);

		// garante que retorna não-null caso encontre o artigo
		Assert.assertNotNull(service.findByTitle("Teste"));
		Assert.assertNotNull(service.findByTitle("Novo"));
		Assert.assertNotNull(service.findByTitle("Outro"));
	}

	@Test
	public void testSave_ValidateInput() {
		WikiPage wiki = new WikiPage();
		wiki.setId(1);
		wiki.setTitle("New_Article");
		wiki.setContents("teste");

		// garante que retorne null caso input=null
		assertThatIllegalArgumentException().isThrownBy(() -> service.save(null));

		// garante que recuse item.title=null
		wiki.setTitle(null);
		assertThatIllegalArgumentException().isThrownBy(() -> service.save(wiki));

		// garante que recuse item.title=""
		wiki.setTitle(null);
		assertThatIllegalArgumentException().isThrownBy(() -> service.save(wiki));
	}

	@Test
	public void testSave_ValidateOperation() {

		WikiPage wiki = new WikiPage();
		wiki.setId(1);
		wiki.setTitle("New_Article");
		wiki.setContents("teste");

		// garante que não retorna null
		WikiPage ret = service.save(wiki);
		Assert.assertNotNull(ret);

		// garante que o name é o mesmo
		Assert.assertEquals(service.save(wiki).getTitle(), wiki.getTitle());

		// garante que o contents é o mesmo
		Assert.assertEquals(service.save(wiki).getContents(), wiki.getContents());

		// garante que sobreescreve o artigo sem gerar erro
		service.save(wiki);

		ret = service.findByTitle(wiki.getTitle());
		Assert.assertNotNull(ret);

		assertEquals(ret.getTitle(), wiki.getTitle());
		assertEquals(ret.getContents(), wiki.getContents());

		wiki.setTitle("Primeiro Teste");
		service.save(wiki);

		wiki.setTitle("Segundo Teste");
		service.save(wiki);

		wiki.setTitle("Terceiro Teste");
		service.save(wiki);

		assertEquals("Primeiro Teste", service.findByTitle("Primeiro Teste").getTitle());
		assertEquals("Segundo Teste", service.findByTitle("Segundo Teste").getTitle());
		assertEquals("Terceiro Teste", service.findByTitle("Terceiro Teste").getTitle());
	}

	@Test
	public void testDelete_ValidateInput() {
		// garante que recusa null
		Assertions.assertThatIllegalArgumentException().isThrownBy(() -> service.delete(null));

		WikiPage wikiPage = new WikiPage();

		// garante que recusa item.name=null
		wikiPage.setTitle(null);
		Assertions.assertThatIllegalArgumentException().isThrownBy(() -> service.delete(null));

		// garante que recusa item.name=""
		wikiPage.setTitle("");
		Assertions.assertThatIllegalArgumentException().isThrownBy(() -> service.delete(null));
	}

	@Test
	public void testDelete_ValidateOperation() {
		WikiPage wikiPage = new WikiPage();
		wikiPage.setTitle("Artigo de Teste");
		wikiPage.setContents("Conteúdo do artigo de teste");

		// exclui o artigo (caso exista) para o próximo teste
		service.delete(wikiPage);

		// garante que retorna false quando não encontrar o artigo para exclusão
		assertFalse(service.delete(wikiPage));

		// adiciona o artigo
		service.save(wikiPage);

		// garante que retorna true quando encontrar o artigo para exclusão
		assertTrue(service.delete(wikiPage));

		// garante que retorna false (o artigo foi mesmo excluído)
		assertFalse(service.delete(wikiPage));
	}

	@Test
	public void testSearch_ValidateInput() {
		// garante que recusa search=null
		assertThatIllegalArgumentException().isThrownBy(() -> service.search(null));

		// garante que recusa search=""
		assertThatIllegalArgumentException().isThrownBy(() -> service.search(""));

		Set<WikiPage> ret = service.search("teste-not-found");

		// garante que busca não encontrada não retorne null
		assertNotNull(ret);

		// garante que busca não encontrada retorne set vazio
		assertEquals(ret.size(), 0);

		service.save(new WikiPage("Teste", "Artigo de teste"));
		service.save(new WikiPage("Novo", "Artigo de novo"));

		// garante que algumas buscam encontram a quantidade correta de wikis
		assertEquals(1, service.search("teste").size());
		assertEquals(2, service.search("Artigo").size());
		assertEquals(0, service.search("ausente").size());
	}

}
