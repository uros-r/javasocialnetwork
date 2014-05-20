package u_phoria.javasocialnetwork.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import u_phoria.javasocialnetwork.domain.PostRepository;

@RunWith(MockitoJUnitRunner.class)
public class ReadPostCommandFactoryTest {
	@Mock private PostPrettyPrinter postPrettyPrinter;
	@Mock private PostRepository postRepository;
	private ReadPostsCommandFactory readPostsCommandFactory = new ReadPostsCommandFactory(postRepository, postPrettyPrinter);
	
	@Test
	public void regexShouldMatchAnythingWithoutSpaces() {
		// Assumes no spaces in usernames valid
		assertTrue("a".matches(readPostsCommandFactory.getCommandLineRegex()));
		assertFalse("a b".matches(readPostsCommandFactory.getCommandLineRegex()));
	}

}
