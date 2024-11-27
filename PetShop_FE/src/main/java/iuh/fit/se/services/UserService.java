package iuh.fit.se.services;

import iuh.fit.se.entities.User;
import iuh.fit.se.utils.APResponse;

public interface UserService {

	APResponse registerUser(User user);
	APResponse loginUser(String username, String password);


  
}
