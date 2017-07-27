package be.ceau.gpodder;

public class Tag {

	private String title;
	private String tag;
	private int usage;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getUsage() {
		return usage;
	}

	public void setUsage(int usage) {
		this.usage = usage;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("Tag [title=")
				.append(title)
				.append(", tag=")
				.append(tag)
				.append(", usage=")
				.append(usage)
				.append("]")
				.toString();
	}

}
