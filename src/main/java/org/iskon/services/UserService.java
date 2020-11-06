package org.iskon.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.iskon.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.iskon.models.UserSearch;
public interface UserService {

	User addUser(User user);
	
	User getUserById(int id);
	
	User updateUser(User user);

	void deleteUser(User user);
	
	Optional<User> getUserByEmailId(String username);
	
	List<User> getUsers(UserSearch query);

	Boolean processUser(User params);

}
