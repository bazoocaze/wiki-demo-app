package br.com.jasf.wikidemoapp.model;

public class WikiArticle {

	private int id;
	private String title;
	private String name;
	private String contents;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public WikiArticle clone() throws CloneNotSupportedException {
		WikiArticle ret = new WikiArticle();
		ret.setName(this.getName());
		ret.setTitle(this.getTitle());
		ret.setContents(this.getContents());
		return ret;
	}

}
