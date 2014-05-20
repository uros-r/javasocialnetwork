package u_phoria.javasocialnetwork;

import java.io.PrintWriter;
import java.util.List;

public class CommandLineProcessor {
	private List<CommandFactory> factories;
	
	public CommandLineProcessor(List<CommandFactory> factories) {
		this.factories = factories;
	}
	
	public void process(String line, PrintWriter printWriter) {
		for (CommandFactory f : factories)
			 if (line.matches(f.getCommandLineRegex())) {
				 Command c = f.create(line);
				 c.execute(printWriter);
				 break;	// Only execute one command
			 }
	}

}
