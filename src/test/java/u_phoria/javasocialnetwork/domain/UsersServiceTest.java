package u_phoria.javasocialnetwork.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import u_phoria.javasocialnetwork.infra.QuickAndDirtyInMemoryUserRepository;

public class UsersServiceTest {
	private UserRepository userRepository = new QuickAndDirtyInMemoryUserRepository();
	private UsersService usersService = new UsersService(userRepository);
	private User user = new User("bob");
	
	@Test
	public void retrieveExistingUser() {
		userRepository.update(user);
		User res = usersService.getUser("bob");
		assertEquals(res, user);
	}
	
	@Test
	public void createMissingUser() {
		User res = usersService.getUser("jim");
		assertEquals(res, new User("jim"));
	}
	
	@Test
	public void addFollowerForExistingUser() {
		userRepository.update(user);
		
		usersService.follows("jim", "bob");
		
		assertEquals("bob", usersService.getUser("jim").getFollowedUserNames().get(0));
	}
}
