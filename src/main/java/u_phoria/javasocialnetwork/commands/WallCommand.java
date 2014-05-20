package u_phoria.javasocialnetwork.commands;

import java.io.PrintWriter;
import java.util.List;

import u_phoria.javasocialnetwork.Command;
import u_phoria.javasocialnetwork.domain.Post;
import u_phoria.javasocialnetwork.domain.PostRepository;
import u_phoria.javasocialnetwork.domain.User;
import u_phoria.javasocialnetwork.domain.UsersService;
import u_phoria.javasocialnetwork.domain.Wall;

public class WallCommand implements Command {
	private String userName;
	private UsersService usersService;
	private PostRepository postRepository;
	private PostPrettyPrinter postPrettyPrinter; 
	
	public WallCommand(UsersService usersService, PostRepository postRepository, PostPrettyPrinter postPrettyPrinter, String userName) {
		this.userName = userName;
		this.usersService = usersService;
		this.postRepository = postRepository;
		this.postPrettyPrinter = postPrettyPrinter;
	}

	public void execute(PrintWriter printWriter) {
		User user = usersService.getUser(userName);
		Wall wall = new Wall(user);
		List<Post> posts = wall.getLatestPosts(postRepository);
		for (Post p : posts)
			printWriter.println(postPrettyPrinter.render(p, true));
	}

}
