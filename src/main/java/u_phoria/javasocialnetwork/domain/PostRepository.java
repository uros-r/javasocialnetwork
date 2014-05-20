package u_phoria.javasocialnetwork.domain;

import java.util.List;

public interface PostRepository {

	void addPost(Post post);

	List<Post> getLatestPostsForUser(String name);

	List<Post> getLatestPostsForUsers(List<String> userNames);

}
