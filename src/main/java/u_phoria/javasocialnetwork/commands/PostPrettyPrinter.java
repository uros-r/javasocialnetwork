package u_phoria.javasocialnetwork.commands;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.ocpsoft.prettytime.PrettyTime;

import u_phoria.javasocialnetwork.domain.Post;

public class PostPrettyPrinter extends PrettyTime {
	// Overriding default behavoiur of PrettyTime when
	// timestamp is < 10min in the past 
	protected String formatDateTime(DateTime t) {
		DateTime now = DateTime.now();
		if (t.isAfter(now.minusMinutes(10))) {
			Period p = new Period(t, now);
			int s = p.getMinutes() * 60 + p.getSeconds();
			if (s < 1)
				return "just now";
			if (s == 1)
				return "1 second ago";
			if (s < 60)
				return s + " seconds ago";
			if (s == 60)
				return "1 minute ago";
			return s / 60 + " minutes ago";
		}
		
		return super.format(t.toDate());
	}
	
	public String render(Post p) {
		return render(p, false);
	}
	
	public String render(Post p, boolean prependUserName) {
		StringBuilder sb = new StringBuilder();
		if (prependUserName)
			sb.append(String.format("%s -> ", p.getUserName()));
		sb.append(String.format("%s (%s)", p.getText(), formatDateTime(p.getCreationTime())));
		return sb.toString();
	}

}
