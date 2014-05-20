package u_phoria.javasocialnetwork;


public interface CommandFactory {
	Command create(String commandLine);
	String getCommandLineRegex();
}
