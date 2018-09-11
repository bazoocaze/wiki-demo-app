package br.com.jasf.wikidemoapp.data;

import java.util.*;

import org.springframework.stereotype.Service;

import br.com.jasf.wikidemoapp.model.WikiPage;

@Service
public class WikiServiceMapImpl implements WikiService {

	Map<String, WikiPage> mapTitle;

	public WikiServiceMapImpl() {
		mapTitle = new HashMap<>();
	}

	@Override
	public WikiPage findByTitle(String title) {
		if (title == null)
			throw new IllegalArgumentException("O parâmetro 'title' não pode ser null");
		if (title == "")
			throw new IllegalArgumentException("O parâmetro 'title' não pode ser o string vazio");

		if (mapTitle.containsKey(title)) {
			try {
				return mapTitle.get(title).clone();
			} catch (CloneNotSupportedException ex) {
				throw new RuntimeException(ex);
			}
		} else {
			return null;
		}
	}

	@Override
	public WikiPage save(WikiPage wiki) {
		if (wiki == null)
			throw new IllegalArgumentException("O parâmetro 'wiki' não pode ser null");
		if (wiki.getTitle() == null)
			throw new IllegalArgumentException("O parâmetro 'wiki.title' não pode ser null");
		if (wiki.getTitle() == "")
			throw new IllegalArgumentException("O parâmetro 'wiki.title' não pode ser o string vazio");

		try {
			mapTitle.put(wiki.getTitle(), wiki.clone());
		} catch (CloneNotSupportedException ex) {
			throw new RuntimeException(ex);
		}

		return wiki;
	}

	@Override
	public boolean delete(WikiPage wiki) {
		if (wiki == null)
			throw new IllegalArgumentException("O parâmetro 'wiki' não pode ser null");
		if (wiki.getTitle() == null)
			throw new IllegalArgumentException("O parâmetro 'wiki.title' não pode ser null");
		if (wiki.getTitle() == "")
			throw new IllegalArgumentException("O parâmetro 'wiki.title' não pode ser o string vazio");

		if (mapTitle.containsKey(wiki.getTitle())) {
			mapTitle.remove(wiki.getTitle());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Set<WikiPage> search(String searchText) {
		if (searchText == null)
			throw new IllegalArgumentException("O parâmetro 'searchText' não pode ser null");
		if (searchText == "")
			throw new IllegalArgumentException("O parâmetro 'searchText' não pode ser o string vazio");

		HashSet<WikiPage> ret = new HashSet<>();

		for (WikiPage item : mapTitle.values()) {
			if (item.getTitle().contains(searchText)) {
				ret.add(item);
			} else if (item.getContents().contains(searchText)) {
				ret.add(item);
			}
		}

		return ret;
	}

}
