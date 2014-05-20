package u_phoria.javasocialnetwork.commands;

import java.io.PrintWriter;
import java.util.List;

import u_phoria.javasocialnetwork.Command;
import u_phoria.javasocialnetwork.domain.Post;
import u_phoria.javasocialnetwork.domain.PostRepository;

public class ReadPostsCommand implements Command {
	private String userName;
	private PostRepository postRepository;
	private PostPrettyPrinter postPrettyPrinter;
	
	public ReadPostsCommand(PostRepository postsService, PostPrettyPrinter postPrettyPrinter, String userName) {
		this.postRepository = postsService;
		this.userName = userName;
		this.postPrettyPrinter = postPrettyPrinter;
	}

	public void execute(PrintWriter printWriter) {
		List<Post> posts = postRepository.getLatestPostsForUser(userName);
		for (Post p : posts)
			printWriter.println(postPrettyPrinter.render(p));
	}
}
