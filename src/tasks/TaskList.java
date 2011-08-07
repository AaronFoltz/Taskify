
package tasks;

public class TaskList {

	private String	kind, id, title, selfLink;

	public String getId() {

		return id;
	}

	public String getKind() {

		return kind;
	}

	public String getSelfLink() {

		return selfLink;
	}

	public String getTitle() {

		return title;
	}

	public void setId(String id) {

		this.id = id;
	}

	public void setKind(String kind) {

		this.kind = kind;
	}

	public void setSelfLink(String selfLink) {

		this.selfLink = selfLink;
	}

	public void setTitle(String title) {

		this.title = title;
	}

}
