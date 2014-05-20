package u_phoria.javasocialnetwork.commands;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Test;

import u_phoria.javasocialnetwork.commands.PostPrettyPrinter;
import u_phoria.javasocialnetwork.domain.Post;

public class PostPrettyPrinterTest {
	private PostPrettyPrinter ppp = new PostPrettyPrinter();
	
	@Test
	public void veryRecentTimes() {
		assertEquals("b (just now)", ppp.render(new Post("a", "b", DateTime.now())));
		assertEquals("b (1 second ago)", ppp.render(new Post("a", "b", DateTime.now().minusSeconds(1))));
		assertEquals("b (12 seconds ago)", ppp.render(new Post("a", "b", DateTime.now().minusSeconds(12))));
		assertEquals("b (1 minute ago)", ppp.render(new Post("a", "b", DateTime.now().minusMinutes(1))));
		assertEquals("b (5 minutes ago)", ppp.render(new Post("a", "b", DateTime.now().minusMinutes(5))));
		assertEquals("b (10 minutes ago)", ppp.render(new Post("a", "b", DateTime.now().minusMinutes(10))));
		assertEquals("b (55 minutes ago)", ppp.render(new Post("a", "b", DateTime.now().minusMinutes(55))));
	}
	
	@Test
	public void timesOverAnHourAgo() {
		assertEquals("b (1 hour ago)", ppp.render(new Post("a", "b", DateTime.now().minusHours(1))));
		assertEquals("b (3 days ago)", ppp.render(new Post("a", "b", DateTime.now().minusDays(3))));
		assertEquals("b (4 months ago)", ppp.render(new Post("a", "b", DateTime.now().minusMonths(4))));
	}

	@Test
	public void renderWithUserNamePrepended() {
		String res = ppp.render(new Post("a", "b", DateTime.now().minusMonths(4)), true);
		assertEquals("a -> b (4 months ago)", res);
	}
}
