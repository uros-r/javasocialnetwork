package u_phoria.javasocialnetwork.domain;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Test;

public class PostTest {
	private DateTime now = DateTime.now();
	private Post post = new Post("jim", "hello", now);
	
	@Test
	public void comparisonBasedOnDates() {
		assertTrue(0 <  post.compareTo(new Post("jim", "hi", now.minus(1000))));
		assertTrue(0 == post.compareTo(new Post("jim", "ho", now)));
		assertTrue(0 >  post.compareTo(new Post("jim", "hum", now.plus(1000))));
	}

}
