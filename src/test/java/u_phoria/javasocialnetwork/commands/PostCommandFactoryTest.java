package u_phoria.javasocialnetwork.commands;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import u_phoria.javasocialnetwork.domain.PostRepository;

@RunWith(MockitoJUnitRunner.class)
public class PostCommandFactoryTest {
	@Mock private PostRepository postRepository;
	private PostCommandFactory postCommandFactory = new PostCommandFactory(postRepository);
	
	@Test
	public void regexMatchesPostCommandsCorrectly() {
		assertTrue("a -> hi".matches(postCommandFactory.getCommandLineRegex()));
		assertFalse(" -> hi".matches(postCommandFactory.getCommandLineRegex()));
		assertFalse("  a -> ".matches(postCommandFactory.getCommandLineRegex()));
		assertFalse("a > hi".matches(postCommandFactory.getCommandLineRegex()));
	}

}
