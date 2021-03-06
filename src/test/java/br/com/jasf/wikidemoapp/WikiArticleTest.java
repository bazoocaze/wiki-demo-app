package br.com.jasf.wikidemoapp;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.jasf.wikidemoapp.model.WikiPage;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WikiArticleTest {

	@Test
	public void testClone() throws CloneNotSupportedException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		
		WikiPage src = new WikiPage();
		WikiPage dst;

		src.setId(10);
		src.setTitle("bravo");
		src.setContents("charlie");

		dst = src.clone();

		Class<WikiPage> class1 = WikiPage.class;
		Method[] methodList = class1.getMethods();

		for (Method method : methodList) {
			if (method.getName().startsWith("get")) {
				Object v1 = method.invoke(src, (Object[]) null);
				Object v2 = method.invoke(dst, (Object[]) null);
				assertEquals(String.format("Value mismatch in method '%s.%s' for clone pattern:",
						class1.getSimpleName(), method.getName()), v1, v2);
			}
		}
	}

}
