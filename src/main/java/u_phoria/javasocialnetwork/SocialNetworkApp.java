package u_phoria.javasocialnetwork;

import java.io.Console;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import u_phoria.javasocialnetwork.commands.FollowCommandFactory;
import u_phoria.javasocialnetwork.commands.PostPrettyPrinter;
import u_phoria.javasocialnetwork.commands.PostsCommandFactory;
import u_phoria.javasocialnetwork.commands.ReadPostsCommandFactory;
import u_phoria.javasocialnetwork.commands.WallCommandFactory;
import u_phoria.javasocialnetwork.domain.PostRepository;
import u_phoria.javasocialnetwork.domain.UserRepository;
import u_phoria.javasocialnetwork.domain.UsersService;
import u_phoria.javasocialnetwork.infra.QuickAndDirtyInMemoryPostRepository;
import u_phoria.javasocialnetwork.infra.QuickAndDirtyInMemoryUserRepository;

public class SocialNetworkApp {
	private CommandLineProcessor commandLineProcessor;

	public SocialNetworkApp(CommandLineProcessor commandLineProcessor) {
		this.commandLineProcessor = commandLineProcessor;
	}

	public void run() {
		while(true) {
			Console console = System.console();
			PrintWriter writer = console.writer();
			writer.print("> ");
			writer.flush();
			String lastRead = console.readLine().trim();
			if ("exit".equals(lastRead))
				break;
			commandLineProcessor.process(lastRead, console.writer());
		}
	}

	public static void main(String[] args) {
		PostRepository postRepository = new QuickAndDirtyInMemoryPostRepository();
		UserRepository userRepository = new QuickAndDirtyInMemoryUserRepository();
		PostPrettyPrinter postPrettyPrinter = new PostPrettyPrinter();
		UsersService usersService = new UsersService(userRepository);
		List<CommandFactory> factories = Arrays.asList(new CommandFactory[] {
				new PostsCommandFactory(postRepository),
				new FollowCommandFactory(usersService),
				new WallCommandFactory(usersService, postRepository, postPrettyPrinter),
				new ReadPostsCommandFactory(postRepository, postPrettyPrinter)
		});
		CommandLineProcessor commandLineProcessor = new CommandLineProcessor(factories);
		SocialNetworkApp socialNetworkApp = new SocialNetworkApp(commandLineProcessor);
		socialNetworkApp.run();
	}
}
