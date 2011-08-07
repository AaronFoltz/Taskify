
package tasks;

public class TaskLists {

	private String		kind, etag;
	private TaskList[]	items;

	public String getEtag() {

		return etag;
	}

	public TaskList[] getItems() {

		return items;
	}

	public String getKind() {

		return kind;
	}

	public void setEtag(String etag) {

		this.etag = etag;
	}

	public void setItems(TaskList[] items) {

		this.items = items;
	}

	public void setKind(String kind) {

		this.kind = kind;
	}

	@Override
	public String toString() {

		return items.toString();
	}

}
