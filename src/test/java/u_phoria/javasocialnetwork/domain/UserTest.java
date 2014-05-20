package u_phoria.javasocialnetwork.domain;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {
	private static final String FOLLOWED_USER_NAME = "schmoe";
	private static final String name = "joe";
	private Post p = new Post("someone", "p");
	@Mock private PostRepository postRepository;

	@Before
	public void before() {
		when(postRepository.getLatestPostsForUser(name)).thenReturn(Arrays.asList(new Post[] {p}));
	}
	
	@Test
	public void shouldHaveAName() {
		assertEquals(name, new User(name).getName());
	}
	
	@Test
	public void shouldGetFollowedUsersForUser() {
		User user = new User(name, new HashSet<String>(Arrays.asList(new String[] {FOLLOWED_USER_NAME})));
		
		List<String> followedUserNames = user.getFollowedUserNames();
		
		assertEquals(Arrays.asList(new String[] {FOLLOWED_USER_NAME}), followedUserNames);
	}
	
	@Test
	public void shouldAddFollowedUserExactlyOnce() {
		User user = new User(name, new HashSet<String>(Arrays.asList(new String[] {FOLLOWED_USER_NAME})));
		user.follow("bob");
		user.follow("bob");
		assertEquals(Arrays.asList(new String[] {"bob", FOLLOWED_USER_NAME}), user.getFollowedUserNames());
	}
}
