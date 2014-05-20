package u_phoria.javasocialnetwork.commands;

import java.io.PrintWriter;

import u_phoria.javasocialnetwork.Command;
import u_phoria.javasocialnetwork.domain.Post;
import u_phoria.javasocialnetwork.domain.PostRepository;

public class PostCommand implements Command {
	private PostRepository postsRepository;
	private String userName;
	private String text;

	public PostCommand(PostRepository postsRepository, String userName, String text) {
		this.postsRepository = postsRepository;
		this.userName = userName;
		this.text = text;
	}

	public void execute(PrintWriter printWriter) {
		Post p = new Post(userName, text);
		postsRepository.addPost(p);
	}

}
