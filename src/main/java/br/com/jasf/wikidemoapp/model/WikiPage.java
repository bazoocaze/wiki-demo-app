package br.com.jasf.wikidemoapp.model;

public class WikiPage {

	private int id;
	private String title;
	private String contents;

	public WikiPage() {
	}

	public WikiPage(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}

	public WikiPage(int id, String title, String contents) {
		this.id = id;
		this.title = title;
		this.contents = contents;
	}

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

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public WikiPage clone() throws CloneNotSupportedException {
		WikiPage ret = new WikiPage();
		ret.setId(this.getId());
		ret.setTitle(this.getTitle());
		ret.setContents(this.getContents());
		return ret;
	}

}
