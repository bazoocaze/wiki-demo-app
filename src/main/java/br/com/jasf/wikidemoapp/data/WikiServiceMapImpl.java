package br.com.jasf.wikidemoapp.data;

import java.util.*;

import br.com.jasf.wikidemoapp.model.WikiArticle;

public class WikiServiceMapImpl implements WikiService {

	Map<String, WikiArticle> mapName;

	public WikiServiceMapImpl() {
		mapName = new HashMap<>();
	}

	@Override
	public WikiArticle findByName(String name) {
		if (name == null)
			throw new IllegalArgumentException("O parâmetro 'name' não pode ser null");
		if (name == "")
			throw new IllegalArgumentException("O parâmetro 'name' não pode ser o string vazio");

		if (mapName.containsKey(name)) {
			try {
				return mapName.get(name).clone();
			} catch (CloneNotSupportedException ex) {
				throw new RuntimeException(ex);
			}
		} else {
			return null;
		}
	}

	@Override
	public WikiArticle save(WikiArticle item) {
		if (item == null)
			throw new IllegalArgumentException("O parâmetro 'item' não pode ser null");
		if (item.getName() == null)
			throw new IllegalArgumentException("O parâmetro 'item.name' não pode ser null");
		if (item.getName() == "")
			throw new IllegalArgumentException("O parâmetro 'item.name' não pode ser o string vazio");
		if (item.getTitle() == null)
			throw new IllegalArgumentException("O parâmetro 'item.title' não pode ser null");
		if (item.getTitle() == "")
			throw new IllegalArgumentException("O parâmetro 'item.title' não pode ser o string vazio");

		try {
			mapName.put(item.getName(), item.clone());
		} catch (CloneNotSupportedException ex) {
			throw new RuntimeException(ex);
		}

		return item;
	}

	@Override
	public boolean delete(WikiArticle item) {
		if (item == null)
			throw new IllegalArgumentException("O parâmetro 'item' não pode ser null");
		if (item.getName() == null)
			throw new IllegalArgumentException("O parâmetro 'item.name' não pode ser null");
		if (item.getName() == "")
			throw new IllegalArgumentException("O parâmetro 'item.name' não pode ser o string vazio");

		if (mapName.containsKey(item.getName())) {
			mapName.remove(item.getName());
			return true;
		} else {
			return false;
		}
	}

}
