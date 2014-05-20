package u_phoria.javasocialnetwork.domain;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WallTest {
	private Wall wall;
	private User user;
	private Post p1 = new Post("bob", "hi", DateTime.now().minusHours(4));
	private Post p2 = new Post("joe", "ho", DateTime.now().minusHours(3));
	@Mock private PostRepository postRepository;
	
	@Test
	public void shouldReturnPostsForSelfAndFollowedUsers() {
		user = new User("joe", new HashSet<String>(Arrays.asList(new String[] {"bob"})));
		when(postRepository.getLatestPostsForUsers(Arrays.asList(new String[] {"bob", "joe"})))
			.thenReturn(Arrays.asList(new Post[] {p1,p2}));
		wall = new Wall(user);
				
		List<Post> posts = wall.getLatestPosts(postRepository);
		
		assertEquals(Arrays.asList(new Post[] {p1, p2}), posts);
	}
}