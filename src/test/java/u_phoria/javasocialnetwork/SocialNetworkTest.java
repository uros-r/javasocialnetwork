package u_phoria.javasocialnetwork;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.Console;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SocialNetworkApp.class)
public class SocialNetworkTest {
	private Console console = PowerMockito.mock(Console.class);
	private SocialNetworkApp socialNetworkApp;
	@Mock private PrintWriter printWriter;
	@Mock private CommandLineProcessor commandLineProcessor;

	@Before
	public void before() {		
		PowerMockito.mockStatic(System.class);
		PowerMockito.when(System.console()).thenReturn(console);
		when(console.writer()).thenReturn(printWriter);
		socialNetworkApp = new SocialNetworkApp(commandLineProcessor);
	}
	
	@Test
	public void provideExitFunctionality() {
		when(console.readLine()).thenReturn("exit");
		
		socialNetworkApp.run();
		
		verify(printWriter, never()).println(any());
	}

	@Test
	public void delegateCommandProcessing() {
		when(console.readLine()).thenReturn("aa").thenReturn("exit");
		
		socialNetworkApp.run();
		
		verify(commandLineProcessor ).process("aa", printWriter);
	}
}
