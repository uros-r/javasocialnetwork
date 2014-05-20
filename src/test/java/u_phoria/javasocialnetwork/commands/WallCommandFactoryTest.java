package u_phoria.javasocialnetwork.commands;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import u_phoria.javasocialnetwork.domain.PostRepository;
import u_phoria.javasocialnetwork.domain.UsersService;

@RunWith(MockitoJUnitRunner.class)
public class WallCommandFactoryTest {
	@Mock private UsersService usersService;
	@Mock private PostRepository postRepository;
	@Mock private PostPrettyPrinter postPrettyPrinter;
	private WallCommandFactory wallCommandFactory = new WallCommandFactory(usersService, postRepository, postPrettyPrinter);
	
	@Test
	public void regexShouldMatchWallCommands() {
		assertTrue("wonder wall".matches(wallCommandFactory.getCommandLineRegex()));
		assertFalse("anotherbrickinthewall".matches(wallCommandFactory.getCommandLineRegex()));
		assertFalse("wall mart".matches(wallCommandFactory.getCommandLineRegex()));
	}

}
