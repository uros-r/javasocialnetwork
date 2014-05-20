package u_phoria.javasocialnetwork.commands;

import u_phoria.javasocialnetwork.Command;
import u_phoria.javasocialnetwork.CommandFactory;
import u_phoria.javasocialnetwork.domain.PostRepository;
import u_phoria.javasocialnetwork.domain.UsersService;

public class WallCommandFactory implements CommandFactory{

	private PostPrettyPrinter postPrettyPrinter;
	private PostRepository postRepository;
	private UsersService usersService;

	public WallCommandFactory(UsersService usersService, PostRepository postRepository,
			PostPrettyPrinter postPrettyPrinter) {
		this.postPrettyPrinter = postPrettyPrinter;
		this.postRepository = postRepository;
		this.usersService = usersService;
	}

	public Command create(String commandLine) {
		String[] s = commandLine.split(" wall");
		return new WallCommand(usersService, postRepository, postPrettyPrinter, s[0].trim());
	}

	public String getCommandLineRegex() {
		return ".+\\s+wall";
	}

}
