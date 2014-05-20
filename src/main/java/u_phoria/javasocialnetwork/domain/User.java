package u_phoria.javasocialnetwork.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class User {
	private String name;
	// Choosing to store list of user identifiers (assuming name attrs are unique)
	// to ensure we don't have to fetch all followed users every time we construct
	// a User object
	private Set<String> followedUserNames;

	public User(String name) {
		this(name, new HashSet<String>());
	}
	public User(String name, Set<String> followedUserNames) {
		this.name = name;
		this.followedUserNames = followedUserNames;
	}

	public String getName() {
		return name;
	}

	public List<String> getFollowedUserNames() {
		return new ArrayList<String>(followedUserNames);
	}
	
	public void follow(String followeeName) {
		followedUserNames.add(followeeName);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof User))
			return false;
		return Objects.equals(name, ((User)obj).name);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	
}
