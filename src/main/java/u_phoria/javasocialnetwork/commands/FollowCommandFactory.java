package u_phoria.javasocialnetwork.commands;

import u_phoria.javasocialnetwork.Command;
import u_phoria.javasocialnetwork.CommandFactory;
import u_phoria.javasocialnetwork.domain.UsersService;

public class FollowCommandFactory implements CommandFactory {

	private UsersService usersService;
	
	public FollowCommandFactory(UsersService usersService) {
		this.usersService = usersService;
	}

	public Command create(String commandLine) {
		String[] s = commandLine.split("follows");	// TODO: handle case when follows part of username
		return new FollowCommand(usersService, s[0].trim(), s[1].trim());
	}

	public String getCommandLineRegex() {
		return ".+\\s+follows\\s+.+";
	}

}
