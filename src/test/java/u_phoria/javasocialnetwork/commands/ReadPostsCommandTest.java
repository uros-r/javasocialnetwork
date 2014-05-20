package u_phoria.javasocialnetwork.commands;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import u_phoria.javasocialnetwork.domain.Post;
import u_phoria.javasocialnetwork.domain.PostRepository;

@RunWith(MockitoJUnitRunner.class)
public class ReadPostsCommandTest {
	private static final String USER_NAME = "joe";
	private static final String POST2_TEXT = "post2 (4 minutes ago)";
	private static final String POST1_TEXT = "post1 (2 hours ago)";
	private ReadPostsCommand readPostsCommand;
	@Mock private PrintWriter printWriter;
	@Mock private PostPrettyPrinter postPrettyPrinter;
	@Mock private PostRepository postRepository;
	private Post p1 = new Post(USER_NAME, POST1_TEXT);
	private Post p2 = new Post(USER_NAME, POST2_TEXT);
	
	@Before
	public void before() {
		when(postRepository.getLatestPostsForUser(USER_NAME)).thenReturn(Arrays.asList(new Post[] {p2,p1}));
		when(postPrettyPrinter.render(p1)).thenReturn(POST1_TEXT);
		when(postPrettyPrinter.render(p2)).thenReturn(POST2_TEXT);
		readPostsCommand = new ReadPostsCommand(postRepository, postPrettyPrinter, USER_NAME);
	}
	
	@Test
	public void shouldReadPostsByRetrievingThemAndPrettyPrinting() {
		readPostsCommand.execute(printWriter);
		
		verify(printWriter, times(2)).println(anyString());
	}
}
