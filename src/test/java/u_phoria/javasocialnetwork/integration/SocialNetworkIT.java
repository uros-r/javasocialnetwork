package u_phoria.javasocialnetwork.integration;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.Console;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import u_phoria.javasocialnetwork.CommandFactory;
import u_phoria.javasocialnetwork.CommandLineProcessor;
import u_phoria.javasocialnetwork.SocialNetworkApp;
import u_phoria.javasocialnetwork.commands.FollowCommandFactory;
import u_phoria.javasocialnetwork.commands.PostPrettyPrinter;
import u_phoria.javasocialnetwork.commands.PostCommandFactory;
import u_phoria.javasocialnetwork.commands.ReadPostsCommandFactory;
import u_phoria.javasocialnetwork.commands.WallCommandFactory;
import u_phoria.javasocialnetwork.domain.Post;
import u_phoria.javasocialnetwork.domain.PostRepository;
import u_phoria.javasocialnetwork.domain.User;
import u_phoria.javasocialnetwork.domain.UserRepository;
import u_phoria.javasocialnetwork.domain.UsersService;
import u_phoria.javasocialnetwork.infra.QuickAndDirtyInMemoryPostRepository;
import u_phoria.javasocialnetwork.infra.QuickAndDirtyInMemoryUserRepository;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SocialNetworkApp.class, DateTime.class})
public class SocialNetworkIT {
	@Mock
	private PrintWriter printWriter;
	private Console console = PowerMockito.mock(Console.class);
	private PostRepository postRepository = new QuickAndDirtyInMemoryPostRepository();
	private UserRepository userRepository = new QuickAndDirtyInMemoryUserRepository();
	
	private SocialNetworkApp socialNetworkApp;

	@Before
	public void before() {		
		PowerMockito.mockStatic(System.class);
		PowerMockito.when(System.console()).thenReturn(console);
		when(console.writer()).thenReturn(printWriter);
		
		PostPrettyPrinter postPrettyPrinter = new PostPrettyPrinter();
		UsersService usersService = new UsersService(userRepository);
		List<CommandFactory> factories = Arrays.asList(new CommandFactory[] {
				new PostCommandFactory(postRepository),
				new FollowCommandFactory(usersService),
				new WallCommandFactory(usersService, postRepository, postPrettyPrinter),
				new ReadPostsCommandFactory(postRepository, postPrettyPrinter)
		});
		CommandLineProcessor commandLineProcessor = new CommandLineProcessor(factories);
		socialNetworkApp = new SocialNetworkApp(commandLineProcessor);
	}
	
	@Test
	public void getPostsForTwoUsersDisplaysCorrectly() {
		postRepository.addPost(new Post("Alice", "I love the weather today", DateTime.now().minusMinutes(5)));
		postRepository.addPost(new Post("Bob", "Damn! We lost!", DateTime.now().minusMinutes(2)));
		postRepository.addPost(new Post("Bob", "Good game though.", DateTime.now().minusMinutes(1)));
		when(console.readLine())
				.thenReturn("Alice")
				.thenReturn("Bob")
				.thenReturn("exit");
		
		socialNetworkApp.run();
		
		ArgumentCaptor<String> outCaptor = ArgumentCaptor.forClass(String.class);
		verify(printWriter, times(3)).println(outCaptor.capture());
		assertEquals("I love the weather today (5 minutes ago)", outCaptor.getAllValues().get(0));
		assertEquals("Good game though. (1 minute ago)", outCaptor.getAllValues().get(1));
		assertEquals("Damn! We lost! (2 minutes ago)", outCaptor.getAllValues().get(2));
	}
	
	@Test
	public void addPost() {
		DateTime now = DateTime.now();
		PowerMockito.mockStatic(DateTime.class);
		when(DateTime.now()).thenReturn(now);
		when(console.readLine()).thenReturn("Alice -> I love the weather today").thenReturn("exit");
		
		socialNetworkApp.run();
		
		assertEquals(new Post("Alice", "I love the weather today", now),
					postRepository.getLatestPostsForUser("Alice").get(0));
	}
	
	@Test
	public void followUser() {
		when(console.readLine()).thenReturn("Charlie follows Alice").thenReturn("exit");
		
		socialNetworkApp.run();
		
		assertEquals(new User("Charlie", new HashSet<String>(Arrays.asList(new String[] {"Alice"}))),
					userRepository.lookup("Charlie"));
	}
	
	@Test
	public void getWallPostsForUser() {
		postRepository.addPost(new Post("Charlie", "I'm in New York today! Anyone wants to have a coffee?", DateTime.now().minusSeconds(15)));
		postRepository.addPost(new Post("Alice", "I love the weather today", DateTime.now().minusMinutes(5)));
		postRepository.addPost(new Post("Bob", "Damn! We lost!", DateTime.now().minusMinutes(2)));
		postRepository.addPost(new Post("Bob", "Good game though.", DateTime.now().minusMinutes(1)));
		when(console.readLine())
				.thenReturn("Charlie follows Alice")
				.thenReturn("Charlie follows Bob")
				.thenReturn("Charlie wall")
				.thenReturn("exit");
		
		socialNetworkApp.run();
		
		ArgumentCaptor<String> outCaptor = ArgumentCaptor.forClass(String.class);
		verify(printWriter, times(4)).println(outCaptor.capture());
		assertEquals("Charlie -> I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)", outCaptor.getAllValues().get(0));
		assertEquals("Bob -> Good game though. (1 minute ago)", outCaptor.getAllValues().get(1));
		assertEquals("Bob -> Damn! We lost! (2 minutes ago)", outCaptor.getAllValues().get(2));
		assertEquals("Alice -> I love the weather today (5 minutes ago)", outCaptor.getAllValues().get(3));
	}
}
	
