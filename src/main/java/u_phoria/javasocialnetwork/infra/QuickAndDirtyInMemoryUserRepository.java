package u_phoria.javasocialnetwork.infra;

import java.util.HashSet;
import java.util.Set;

import u_phoria.javasocialnetwork.domain.User;
import u_phoria.javasocialnetwork.domain.UserRepository;

public class QuickAndDirtyInMemoryUserRepository implements UserRepository {
	private Set<User> repo = new HashSet<User>();
	
	public User lookup(String name) {
		for (User u : repo)
			if (u.getName().equals(name))
				return u;
		return null;
	}

	public void update(User user) {
		repo.add(user);
	}
}
