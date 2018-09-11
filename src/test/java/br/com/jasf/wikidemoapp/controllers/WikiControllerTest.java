package br.com.jasf.wikidemoapp.controllers;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(value = SpringRunner.class)
// @WebMvcTest
public class WikiControllerTest {

	// private MockMvc mockMvc;

	@Before
	public void setup() {
		// this.mockMvc = MockMvcBuilders.standaloneSetup(new WikiController()).build();
	}

	@Test
	public void testGetWiki() throws Exception {
		// String a =
		// mockMvc.perform(get("/wiki/Teste").accept(MediaType.TEXT_HTML)).andExpect(status().isOk()).andReturn().getModelAndView().getViewName();
		// assertEquals("sadasdqw", a);
	}

}
