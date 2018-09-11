package br.com.jasf.wikidemoapp;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.Assert.*;

import org.assertj.core.api.Assertions;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.jasf.wikidemoapp.data.WikiServiceMapImpl;
import br.com.jasf.wikidemoapp.model.WikiArticle;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { WikiServiceMapImpl.class })
public class WikiServiceMapImplTest {

	@Autowired
	public WikiServiceMapImpl service;

	@Test
	public void testFindByName_ValidateInput() {

		// garante que recusa name=null
		assertThatIllegalArgumentException().isThrownBy(() -> {
			service.findByName(null);
		});

		// garante que recusa name=""
		assertThatIllegalArgumentException().isThrownBy(() -> {
			service.findByName("");
		});

	}

	@Test
	public void testFindByName_ValidateOperation() {

		// garante que retorna null caso não encontre o artigo
		Assert.assertNull(service.findByName("12345"));

		WikiArticle wiki = new WikiArticle();
		wiki.setId(1);
		wiki.setName("Teste");
		wiki.setTitle("Teste");
		wiki.setContents("teste");

		// salva alguns artigos de teste
		service.save(wiki);

		wiki.setName("Novo");
		wiki.setTitle("Novo");
		service.save(wiki);

		wiki.setName("Outro");
		wiki.setTitle("Outro");
		service.save(wiki);

		// garante que retorna não-null caso encontre o artigo
		Assert.assertNotNull(service.findByName("Teste"));
		Assert.assertNotNull(service.findByName("Novo"));
		Assert.assertNotNull(service.findByName("Outro"));
	}

	@Test
	public void testSave_ValidateInput() {
		WikiArticle wiki = new WikiArticle();
		wiki.setId(1);
		wiki.setName("New_Article");
		wiki.setTitle("New_Article");
		wiki.setContents("teste");

		// garante que retorne null caso input=null
		assertThatIllegalArgumentException().isThrownBy(() -> service.save(null));

		// garante que recuse item.name=null
		wiki.setName(null);
		assertThatIllegalArgumentException().isThrownBy(() -> service.save(wiki));

		// garante que recuse item.name=""
		wiki.setName("");
		assertThatIllegalArgumentException().isThrownBy(() -> service.save(wiki));

		wiki.setName("New_Article");

		// garante que recuse item.title=null
		wiki.setTitle(null);
		assertThatIllegalArgumentException().isThrownBy(() -> service.save(wiki));

		// garante que recuse item.title=""
		wiki.setTitle(null);
		assertThatIllegalArgumentException().isThrownBy(() -> service.save(wiki));
	}

	@Test
	public void testSave_ValidateOperation() {

		WikiArticle wiki = new WikiArticle();
		wiki.setId(1);
		wiki.setName("New_Article");
		wiki.setTitle("New_Article");
		wiki.setContents("teste");

		// garante que não retorna null
		WikiArticle ret = service.save(wiki);
		Assert.assertNotNull(ret);

		// garante que o name é o mesmo
		Assert.assertEquals(service.save(wiki).getName(), wiki.getName());

		// garante que o contents é o mesmo
		Assert.assertEquals(service.save(wiki).getContents(), wiki.getContents());

		// garante que sobreescreve o artigo sem gerar erro
		service.save(wiki);

		ret = service.findByName(wiki.getName());
		Assert.assertNotNull(ret);

		assertEquals(ret.getName(), wiki.getName());
		assertEquals(ret.getTitle(), wiki.getTitle());
		assertEquals(ret.getContents(), wiki.getContents());

		wiki.setName("Primeiro_Teste");
		wiki.setTitle("Primeiro Teste");
		service.save(wiki);

		wiki.setName("Segundo_Teste");
		wiki.setTitle("Segundo_Teste");
		service.save(wiki);

		wiki.setName("Terceiro_Teste");
		wiki.setTitle("Terceiro_Teste");
		service.save(wiki);

		assertEquals(service.findByName("Primeiro_Teste").getName(), "Primeiro_Teste");
		assertEquals(service.findByName("Segundo_Teste").getName(), "Segundo_Teste");
		assertEquals(service.findByName("Terceiro_Teste").getName(), "Terceiro_Teste");
	}

	@Test
	public void testDelete_ValidateInput() {
		// garante que recusa null
		Assertions.assertThatIllegalArgumentException().isThrownBy(() -> service.delete(null));

		WikiArticle wikiArticle = new WikiArticle();

		// garante que recusa item.name=null
		wikiArticle.setName(null);
		Assertions.assertThatIllegalArgumentException().isThrownBy(() -> service.delete(null));

		// garante que recusa item.name=""
		wikiArticle.setName("");
		Assertions.assertThatIllegalArgumentException().isThrownBy(() -> service.delete(null));
	}

	@Test
	public void testDelete_ValidateOperation() {
		WikiArticle wikiArticle = new WikiArticle();
		wikiArticle.setName("Artigo_de_Teste");
		wikiArticle.setTitle("Artigo de Teste");
		wikiArticle.setContents("Conteúdo do artigo de teste");

		// exclui o artigo (caso exista) para o próximo teste
		service.delete(wikiArticle);

		// garante que retorna false quando não encontrar o artigo para exclusão
		assertFalse(service.delete(wikiArticle));

		// adiciona o artigo
		service.save(wikiArticle);

		// garante que retorna true quando encontrar o artigo para exclusão
		assertTrue(service.delete(wikiArticle));

		// garante que retorna false (o artigo foi mesmo excluído)
		assertFalse(service.delete(wikiArticle));
	}

}
