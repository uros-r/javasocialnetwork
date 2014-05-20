package u_phoria.javasocialnetwork.domain;

import java.util.List;

public class Wall {
	private User user;
	
	public Wall(User user) {
		this.user = user;
	}

	public List<Post> getLatestPosts(PostRepository postRepository) {
		List<String> userNames = user.getFollowedUserNames();
		userNames.add(user.getName());
		return postRepository.getLatestPostsForUsers(userNames);
	}
}
