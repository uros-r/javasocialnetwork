package u_phoria.javasocialnetwork.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import u_phoria.javasocialnetwork.domain.UsersService;

@RunWith(MockitoJUnitRunner.class)
public class FollowCommandFactoryTest {
	@Mock private UsersService usersService;
	private FollowCommandFactory followCommandFactory = new FollowCommandFactory(usersService);
	
	@Test
	public void regexMatchesFollowCommandsCorrectly() {
		assertTrue("a follows b".matches(followCommandFactory.getCommandLineRegex()));
		assertFalse(" follows b".matches(followCommandFactory.getCommandLineRegex()));
		assertFalse("a follows ".matches(followCommandFactory.getCommandLineRegex()));
		assertFalse("a follow b".matches(followCommandFactory.getCommandLineRegex()));
	}

}
