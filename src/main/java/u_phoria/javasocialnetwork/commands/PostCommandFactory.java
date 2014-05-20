package u_phoria.javasocialnetwork.commands;

import u_phoria.javasocialnetwork.Command;
import u_phoria.javasocialnetwork.CommandFactory;
import u_phoria.javasocialnetwork.domain.PostRepository;

public class PostCommandFactory implements CommandFactory {
	private PostRepository postRepository;
	
	public PostCommandFactory(PostRepository postsRepository) {
		this.postRepository = postsRepository;
	}

	public Command create(String commandLine) {
		String[] ss = commandLine.split("->");
		return new PostCommand(postRepository, ss[0].trim(), ss[1].trim());
	}

	public String getCommandLineRegex() {
		return ".+\\s+->\\s+.+";
	}

}
