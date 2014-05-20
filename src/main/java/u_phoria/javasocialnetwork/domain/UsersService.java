package u_phoria.javasocialnetwork.domain;

public class UsersService {
	private UserRepository userRepository;
	
	public UsersService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void follows(String followerName, String followeeName) {
		User follower = getUser(followerName);
		if (follower == null) {
			follower = new User(followerName);			
		}
		follower.follow(followeeName);
		userRepository.update(follower);
	}

	public User getUser(String userName) {
		User user = userRepository.lookup(userName);
		if (user == null) {
			user = new User(userName);			
		}
		return user;
	}

}
