package u_phoria.javasocialnetwork.commands;

import java.io.PrintWriter;

import u_phoria.javasocialnetwork.Command;
import u_phoria.javasocialnetwork.domain.UsersService;

public class FollowCommand  implements Command {

	private String followeeName;
	private String followerName;
	private UsersService usersService;

	
	public FollowCommand(UsersService usersService, String followerName, String followeeName) {
		this.followeeName = followeeName;
		this.followerName = followerName;
		this.usersService = usersService;
	}

	public void execute(PrintWriter printWriter) {
		usersService.follows(followerName, followeeName);
	}
}
