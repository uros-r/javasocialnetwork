package u_phoria.javasocialnetwork.domain;

public interface UserRepository {

	User lookup(String name);

	void update(User user);
	
}
