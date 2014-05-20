package u_phoria.javasocialnetwork.domain;

import java.util.Objects;

import org.joda.time.DateTime;

public class Post implements Comparable<Post>{
	private String userName;
	private String text;
	private DateTime creationTime;

	public Post(String userName, String text) {
		this(userName, text, DateTime.now());
	}
	public Post(String userName, String text, DateTime creationTime) {
		this.userName = userName;
		this.text = text;
		this.creationTime = creationTime;
	}
	
	public String getText() {
		return text;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public DateTime getCreationTime() {
		return creationTime;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Post))
			return false;
		return Objects.equals(text, ((Post)obj).text)
				&& Objects.equals(creationTime, ((Post)obj).creationTime)
				&& Objects.equals(userName, ((Post)obj).userName);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(text, userName, creationTime);
	}
	public int compareTo(Post post) {
		return creationTime.compareTo(post.creationTime);
	}
	
}
