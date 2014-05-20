package u_phoria.javasocialnetwork.commands;

import u_phoria.javasocialnetwork.Command;
import u_phoria.javasocialnetwork.CommandFactory;
import u_phoria.javasocialnetwork.domain.PostRepository;

public class ReadPostsCommandFactory implements CommandFactory {
	private PostRepository postRepository;
	private PostPrettyPrinter postPrettyPrinter;

	public ReadPostsCommandFactory(PostRepository postRepository,
			PostPrettyPrinter postPrettyPrinter) {
		this.postRepository = postRepository;
		this.postPrettyPrinter = postPrettyPrinter;
	}

	public Command create(String userName) {
		return new ReadPostsCommand(postRepository, postPrettyPrinter, userName);
	}

	public String getCommandLineRegex() {
		return "\\S*";
	}

}
