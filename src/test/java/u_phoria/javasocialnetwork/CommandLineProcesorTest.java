package u_phoria.javasocialnetwork;

import static org.mockito.Mockito.verify;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import u_phoria.javasocialnetwork.Command;
import u_phoria.javasocialnetwork.CommandFactory;
import u_phoria.javasocialnetwork.CommandLineProcessor;

@RunWith(MockitoJUnitRunner.class)
public class CommandLineProcesorTest {
	private CommandLineProcessor commandLineProcessor;
	@Mock private PrintWriter printWriter;
	@Mock private Command myCommand;
	private CommandFactory myCommandFactory = new CommandFactory() {
		public Command create(String line) { return myCommand; }
		public String getCommandLineRegex() { return ".*omm.*"; };
	};
	
	@Before
	public void before() {
		List<CommandFactory> m = new ArrayList<CommandFactory>();
		m.add(myCommandFactory);
		commandLineProcessor = new CommandLineProcessor(m);
	}

	@Test
	public void shouldMatchAndExecuteAppropriateCommand() {
		commandLineProcessor.process("command", printWriter);
		
		verify(myCommand).execute(printWriter);
	}

}
