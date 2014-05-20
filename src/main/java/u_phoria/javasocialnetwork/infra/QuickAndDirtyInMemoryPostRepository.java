package u_phoria.javasocialnetwork.infra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import u_phoria.javasocialnetwork.domain.Post;
import u_phoria.javasocialnetwork.domain.PostRepository;

public class QuickAndDirtyInMemoryPostRepository implements PostRepository {
	private List<Post> repo = new ArrayList<Post>();

	public List<Post> getLatestPostsForUser(String name) {
		return getLatestPostsForUsers(Arrays.asList(new String[] {name}));
	}
	
	public List<Post> getLatestPostsForUsers(List<String> userNames) {
		List<Post> res = new ArrayList<Post>();
		for (Post p : repo)
			if (userNames.contains(p.getUserName()))
				res.add(p);
		Collections.sort(res);
		Collections.reverse(res);
		return res;
	}
	
	public void addPost(Post post) {
		repo.add(post);
	}
}
